# Alexis User Guide

![Ui.png](docs/Ui.png)

This is a project template for a greenfield Java project. It's named after the Java mascot _Alexis_. Given below are instructions on how to use it.

## Quick Start

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file from here.
3. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar alexis.jar command to run the application. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

## Command line features
    Type the command in the command box and press Enter to execute it.
    Some example commands you can try:

# TODO (Creates a new todo task)
    Example: `todo (arguments for description)`
    expected output: 'Got it. I've added this task
                      [T][] description
                      Now you have total_number_of_tasks in the list'

## Deadline (Creates a new deadline task)
    Example: `deadline (arguments for description) /by (do by date)`
    expected output: 'Got it. I've added this task
                      [D][] description (by: by_date)
                      Now you have total_number_of_tasks in the list'

## Event (Creates a new Event task)
    Example: `event (arguments for description) /from (start date) /to (end date)`
    expected output: 'Got it. I've added this task
                      [E][] description (from: start_date to: end_date)
                      Now you have total_number_of_tasks in the list'

## Mark (Marks a task as done)
    Example: `Mark (task index)`
    expected output: 'Nice! I've marked this task as done:
                      [T][X] description'

## Unmark (Marks a task as not done)
    Example: `unmark (task index)`
    expected output: 'OK, I've marked this task as not done yet:
                      [T][X] description'

## Delete (deletes a task)
    Example: `delete (task index)`
    expected output: 'Noted. I've removed this task:
                      [E][] description (from: start_date to: end_date)
                      Now you have total_number_of_tasks in the list'

## List (lists all the tasks)
    Example: `list`
    expected output: 'Here are the tasks in your list:
                      1. [T][X] description'

# Search (Returns all tasks whereby the description contains the search_string)
    Example: `search description`
    expected output: 'Here are the matching tasks in your list:
                      1. [T][X] description'
# Bye (perform a save)
    Example: `search description`
    expected output: 'Bye. Hope to see you again soon!'
