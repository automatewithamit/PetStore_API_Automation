#!/bin/bash

stop_mongodb_container() {
    if [ "$(docker ps -q -f name=mongodb)" ]; then
        echo "Stopping MongoDB container..."
        docker-compose down
    else
        echo "MongoDB container is not running."
    fi
}

stop_sqldb_container() {
    if [ "$(docker ps -q -f name=sqldb)" ]; then
        echo "Stopping SQL Database container..."
        docker-compose -f docker-compose-sql.yml down
    else
        echo "SQL Database container is not running."
    fi
}

stop_oracledb_container() {
    if [ "$(docker ps -q -f name=oracledb)" ]; then
        echo "Stopping Oracle Database container..."
        docker-compose -f docker-compose-oracle.yml down
    else
        echo "Oracle Database container is not running."
    fi
}

case $1 in
    "mongodb")
        stop_mongodb_container
        ;;
    "sql")
        stop_sqldb_container
        ;;
    "oracle")
        stop_oracledb_container
        ;;
    *)
        echo "Invalid database type. Please specify 'mongodb', 'sql', or 'oracle'."
        exit 1
        ;;
esac
