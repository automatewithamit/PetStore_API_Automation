#!/bin/bash

start_docker_mac() {
    if ! docker info >/dev/null 2>&1; then
        echo "Starting Docker..."
        open --background -a Docker
        # Wait until Docker daemon is running and has completed initial setup
        while (! docker stats --no-stream >/dev/null 2>&1); do
            echo "Waiting for Docker to launch..."
            sleep 1
        done
        echo "Docker started."
    fi
}

start_docker_windows() {
    if ! docker info >/dev/null 2>&1; then
        echo "Starting Docker..."
        powershell.exe -Command "Start-Process 'Docker Desktop' -Verb RunAs"
        # Wait until Docker daemon is running and has completed initial setup
        while (! docker stats --no-stream >/dev/null 2>&1); do
            echo "Waiting for Docker to launch..."
            sleep 1
        done
        echo "Docker started."
    fi
}

start_mongodb_container() {
    if [ "$(docker ps -q -f name=mongodb)" ]; then
        echo "MongoDB container is already running."
    else
        echo "Starting MongoDB container..."
        docker-compose up -d
    fi
}

start_sqldb_container() {
    if [ "$(docker ps -q -f name=sqldb)" ]; then
        echo "SQL Database container is already running."
    else
        echo "Starting SQL Database container..."
        docker-compose -f docker-compose-sql.yml up -d
    fi
}

start_oracledb_container() {
    if [ "$(docker ps -q -f name=oracledb)" ]; then
        echo "Oracle Database container is already running."
    else
        echo "Starting Oracle Database container..."
        docker-compose -f docker-compose-oracle.yml up -d
    fi
}

case $1 in
    "mongodb")
        if [[ "$OSTYPE" == "darwin"* ]]; then
            start_docker_mac
        elif [[ "$OSTYPE" == "msys" ]]; then
            start_docker_windows
        fi
        start_mongodb_container
        ;;
    "sql")
        if [[ "$OSTYPE" == "darwin"* ]]; then
            start_docker_mac
        elif [[ "$OSTYPE" == "msys" ]]; then
            start_docker_windows
        fi
        start_sqldb_container
        ;;
    "oracle")
        if [[ "$OSTYPE" == "darwin"* ]]; then
            start_docker_mac
        elif [[ "$OSTYPE" == "msys" ]]; then
            start_docker_windows
        fi
        start_oracledb_container
        ;;
    *)
        echo "Invalid database type. Please specify 'mongodb', 'sql', or 'oracle'."
        exit 1
        ;;
esac
