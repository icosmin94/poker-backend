#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER keycloak PASSWORD 'keycloak';
    CREATE DATABASE keycloak;
    GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

    CREATE USER user_management PASSWORD 'user_management';
    CREATE DATABASE user_management;
    GRANT ALL PRIVILEGES ON DATABASE user_management TO user_management;

    CREATE USER deck_management PASSWORD 'deck_management';
    CREATE DATABASE deck_management;
    GRANT ALL PRIVILEGES ON DATABASE deck_management TO deck_management;

    CREATE USER table_management PASSWORD 'table_management';
    CREATE DATABASE table_management;
    GRANT ALL PRIVILEGES ON DATABASE table_management TO table_management;
EOSQL