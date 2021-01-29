CREATE DATABASE "borrow" OWNER postgres;
\connect borrow
ALTER DATABASE "borrow" SET TIMEZONE TO 'Europe/Rome';
SET TIMEZONE TO 'Europe/Rome';

CREATE TABLE "borrows"
(
    borrow_id integer,
    start_borrow character varying,
    end_borrow character varying,
    book integer,
    customer integer,
    notify character varying
) TABLESPACE pg_default;

ALTER TABLE "borrow"
    OWNER to postgres;