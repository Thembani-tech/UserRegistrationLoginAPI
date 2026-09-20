package com.thembani.api;

import com.thembani.api.model.User;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<User> users = new ArrayList<>();

    public static Javalin createApp() {

        Javalin app = Javalin.create();

        app.get("/", ctx ->
                ctx.result("User Registration & Login API is running!")
        );

        app.post("/register", ctx -> {

            User user = ctx.bodyAsClass(User.class);

            if (user.getUsername() == null || user.getUsername().isBlank()
                    || user.getPassword() == null || user.getPassword().isBlank()) {

                ctx.status(400);
                ctx.result("Username and password are required");
                return;
            }

            for (User existingUser : users) {

                if (existingUser.getUsername().equals(user.getUsername())) {

                    ctx.status(409);
                    ctx.result("Username already exists");
                    return;
                }
            }

            users.add(user);

            ctx.status(201);
            ctx.json(user);
        });

        app.post("/login", ctx -> {

            User loginUser = ctx.bodyAsClass(User.class);

            for (User user : users) {

                if (user.getUsername().equals(loginUser.getUsername())
                        && user.getPassword().equals(loginUser.getPassword())) {

                    ctx.status(200);
                    ctx.result("Login successful");
                    return;
                }
            }

            ctx.status(401);
            ctx.result("Invalid username or password");
        });

        return app;
    }

    // Test-support hook: clears the in-memory "database" between tests
    static void resetUsers() {
        users.clear();
    }

    public static void main(String[] args) {
        createApp().start(7000);
    }
}