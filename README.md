# Rewards Program (Spring Boot)

## Overview
This application calculates reward points for customers based on their transactions over a configurable number of months.

The reward calculation is dynamic and configurable via application properties.

---

## Reward Calculation Logic

- 1 point for every dollar spent between $50 and $100
- 2 points for every dollar spent above $100

### Example:
For $120:
- 50 points (for $50–$100)
- 40 points (for above $100)
- Total = 90 points

---

## Features

- Dynamic month configuration (no hardcoding)
- Monthly and total reward calculation
- REST API endpoints
- H2 in-memory database
- SQL-based data initialization
- Exception handling
- Unit, controller, and integration tests

---

## Configuration

In `application.properties`:
