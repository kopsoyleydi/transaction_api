CREATE TABLE city (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      short_name VARCHAR(255) NOT NULL
);

CREATE TABLE permission (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            usertype INTEGER NOT NULL
);

CREATE TABLE t_user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      limit_sum DOUBLE,
                      limit_datetime TIMESTAMP,
                      limit_currency_shortname VARCHAR(255),
                      surname VARCHAR(255),
                      client_iin VARCHAR(255),
                      birth_date VARCHAR(255),
                      address VARCHAR(255),
                      balance DOUBLE,
                      city_id BIGINT,
                      FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE t_user_permissions (
                                 user_id BIGINT,
                                 permissions_id BIGINT,
                                 FOREIGN KEY (user_id) REFERENCES t_user(id),
                                 FOREIGN KEY (permissions_id) REFERENCES permission(id),
                                 PRIMARY KEY (user_id, permissions_id)
);


