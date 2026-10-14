package com.course.cleancode.refactoring;

/**
 * STARTER CODE:
 * Conditional switch statement checking type codes.
 */
public class EmployeeCompensation {

    public enum EmployeeType {
        ENGINEER, MANAGER, EXECUTIVE
    }

    public static class Employee {
        private final EmployeeType type;
        private final double salary;

        public Employee(EmployeeType type, double salary) {
            this.type = type;
            this.salary = salary;
        }

        public double calculateBonus() {
            return switch (type) {
                case ENGINEER -> salary * 0.10;
                case MANAGER -> salary * 0.25;
                case EXECUTIVE -> salary * 0.50;
            };
        }

        public double getSalary() { return salary; }
    }
}
