CREATE DATABASE db_library_system;

CREATE TABLE "users" (
                         "id" BIGSERIAL PRIMARY KEY NOT NULL,
                         "firt_name" varchar(255) NOT NULL,
                         "last_name" varchar(255) NOT NULL,
                         "password" varchar(255) NOT NULL,
                         "email" varchar(255) UNIQUE NOT NULL,
                         "phone_number" varchar(10),
                         "avatar_key" varchar(100),
                         "date_of_birth" bigint NOT NULL,

                         "is_active" boolean NOT NULL DEFAULT true,
                         "version" bigint NOT NULL DEFAULT 0,
                         "created_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint,
                         "updated_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint
);

CREATE TABLE "roles" (
                         "id" BIGSERIAL PRIMARY KEY NOT NULL,
                         "role_name" varchar NOT NULL,
                         "is_active" boolean NOT NULL DEFAULT true,
                         "version" bigint NOT NULL DEFAULT 0,
                         "created_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint,
                         "updated_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint
);

CREATE TABLE "user_roles" (
                              "id" BIGSERIAL PRIMARY KEY NOT NULL,
                              "user_id" bigint NOT NULL,
                              "role_id" bigint NOT NULL,
                              "is_active" boolean NOT NULL DEFAULT true,
                              "version" bigint NOT NULL DEFAULT 0,
                              "created_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint,
                              "updated_at" bigint NOT NULL DEFAULT (EXTRACT(EPOCH FROM now()) * 1000)::bigint
);



ALTER TABLE "user_roles" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_roles" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");