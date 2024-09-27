# GraphQL Canvas Explorer

This project delivers a command-line interface (CLI) application aimed at streamlining course and assignment management for educational platforms that utilize **GraphQL APIs**. Built using **Spring Boot**, **Picocli**, and **GraphQL**, this application provides a convenient and efficient way for administrators or educators to interact with course and assignment data directly from the terminal.

The primary business value lies in simplifying the management of active and inactive courses and assignments, allowing for improved oversight and streamlined data retrieval processes. This solution minimizes the need for manual intervention in viewing and organizing educational content, increasing productivity and operational efficiency.

For this project, we developed a **Spring Boot command line program** utilizing **Picocli** and **GraphQL** to interact with course and assignment data via a CLI interface. The main functionality is divided into two subcommands: `list-courses` and `list-assignments`. Each subcommand has options for listing active or inactive entities, providing a straightforward way to manage and view information.

### Subcommand 1: `list-courses`
- **Options**: `--active/--no-active` 
  - Default: `--active` (lists only active courses).
  - Use `--no-active` to list inactive courses as well.
  
### Subcommand 2: `list-assignments`
- **Arguments**: Course name
- **Options**: `--active/--no-active`
  - Default: `--active` (lists only active assignments).
  - Use `--no-active` to list inactive assignments as well.
  
If an exact match for the course name is not found, the program searches for a course with the provided substring. In case of multiple matches, it informs the user and lists the matching courses.

The assignments listed include:
- Assignment name
- Due date

### Security Note: Token Handling
You must pass a **Canvas API token** securely using the `--token` option. **Do not include your token in the code**—this ensures the token is not exposed in version control (e.g., GitHub). Failing to adhere to this may lead to security risks.

### Build System
We used **Maven** for project build and dependency management. Ensure that the project compiles successfully before submission to avoid penalties, as non-compiling submissions will not be graded.

### Example CLI Runs

```bash
# List active courses
$ java -jar cli.jar list-courses --token <your-token> --active

# List inactive assignments for a specific course
$ java -jar cli.jar list-assignments "Introduction to GraphQL" --token <your-token> --no-active
