CREATE TABLE vinyl (
                       id                  BIGSERIAL PRIMARY KEY NOT NULL,
                       title               varchar(255) NOT NULL,
                       author              varchar(255) NOT NULL,
                       year                integer NOT NULL,
                       created_date        timestamp NOT NULL,
                       last_modified_date  timestamp NOT NULL,
                       version             integer NOT NULL
);