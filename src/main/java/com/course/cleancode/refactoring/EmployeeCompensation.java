package com.course.cleancode.refactoring;

/**
 * CLEAN CODE SOLUTION:
 * Replace Conditional with Polymorphism:
 * Eliminates switch on type codes by subclassing an abstract Employee class.
 */
public class EmployeeCompensation {

    public static abstract class Employee {
        private final double salary;

        protected Employee(double salary) {
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }

        public abstract double calculateBonus();
    }

    public static class Engineer extends Employee {
        public Engineer(double salary) {
            super(salary);
        }

        @Override
        public double calculateBonus() {
            return getSalary() * 0.10;
        }
    }

    public static class Manager extends Employee {
        public Manager(double salary) {
            super(salary);
        }

        @Override
        public double calculateBonus() {
            return getSalary() * 0.25;
        }
    }

    public static class Executive extends Employee {
        public Executive(double salary) {
            super(salary);
        }

        @Override
        public double calculateBonus() {
            return getSalary() * 0.50;
        }
    }
}
