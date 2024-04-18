CREATE TABLE city (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      short_name VARCHAR(255) NOT NULL
);

CREATE TABLE permission (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            usertype ENUM ('INDIVIDUAL', 'LEGAL', 'Individual_Entrepreneur') NOT NULL
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      limit_sum DOUBLE,
                      remaining_limit DOUBLE,
                      limit_datetime TIMESTAMP,
                      limit_currency_shortname VARCHAR(255),
                      surname VARCHAR(255),
                      clientIin VARCHAR(255),
                      birthDate VARCHAR(255),
                      address VARCHAR(255),
                      balance DOUBLE,
                      city_id BIGINT,
                      FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE user_permission (
                                 user_id BIGINT,
                                 permission_id BIGINT,
                                 FOREIGN KEY (user_id) REFERENCES user(id),
                                 FOREIGN KEY (permission_id) REFERENCES permission(id),
                                 PRIMARY KEY (user_id, permission_id)
);


