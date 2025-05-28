CREATE TABLE `Member`
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT,
    `email`     varchar(255) UNIQUE NOT NULL,
    `image`     varchar(255),
    `password`  varchar(255),
    `nickname`  varchar(255),
    `created_at` datetime,
    `update_at`  datetime,
    `active`    boolean
);

CREATE TABLE `Post`
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT,
    `writer_id` bigint NOT NULL,
    `content`   text,
    `created_at` datetime,
    `updated_at` datetime
);

CREATE TABLE `Comment`
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT,
    `post_id`   bigint NOT NULL,
    `writer_id` bigint NOT NULL,
    `content`   text,
    `created_at` datetime,
    `updated_at` datetime
);

CREATE TABLE `PostLike`
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT,
    `member_id` bigint NOT NULL,
    `post_id`   bigint NOT NULL,
    `liked_at`   datetime
);

CREATE TABLE `CommentLike`
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `member_id`  bigint NOT NULL,
    `comment_id` bigint NOT NULL,
    `liked_at`    datetime
);

CREATE TABLE `Follow`
(
    `id`           bigint PRIMARY KEY AUTO_INCREMENT,
    `follower_id`  bigint NOT NULL,
    `following_id` bigint NOT NULL,
    `followed_at`   datetime
);

ALTER TABLE `Post`
    ADD FOREIGN KEY (`writer_id`) REFERENCES `Member` (`id`);

ALTER TABLE `Comment`
    ADD FOREIGN KEY (`post_id`) REFERENCES `Post` (`id`);

ALTER TABLE `Comment`
    ADD FOREIGN KEY (`writer_id`) REFERENCES `Member` (`id`);

ALTER TABLE `PostLike`
    ADD FOREIGN KEY (`post_id`) REFERENCES `Post` (`id`);

ALTER TABLE `CommentLike`
    ADD FOREIGN KEY (`comment_id`) REFERENCES `Comment` (`id`);

ALTER TABLE `Follow`
    ADD FOREIGN KEY (`follower_id`) REFERENCES `Member` (`id`);

ALTER TABLE `Follow`
    ADD FOREIGN KEY (`following_id`) REFERENCES `Member` (`id`);
