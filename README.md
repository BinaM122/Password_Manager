#Password Manager

A simple, lightweight password manager built with Java Swing that allows users to store, manage, and generate secure passwords locally.

## Features

- **Add Passwords**: Store passwords for websites and applications
- **Manual or Random Generation**: Choose to enter your own password or generate a secure random one
- **View All Passwords**: Display all stored passwords in an easy-to-read format
- **Remove Passwords**: Delete passwords you no longer need
- **Local Storage**: All passwords are saved locally in a text file on your machine

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required (uses built-in Java libraries)

## Installation

1. Save the code as `PasswordManager.java`
2. Compile the program:
```bash
   javac PasswordManager.java
```
3. Run the program:
```bash
   java main.PasswordManager
```

## Usage

When you launch the application, you'll see a menu with four options:

### 1. Add Password
- Enter the website or app name
- Choose between:
  - **Manual**: Enter your own password
  - **Random**: Generate a 12-character secure password automatically
- The password is saved immediately

### 2. Remove Password
- Enter the website or app name
- The associated password will be deleted from storage

### 3. View All Passwords
- Displays all stored passwords in a scrollable window
- Shows website names alongside their passwords

### 4. Exit
- Closes the application

## Data Storage

Passwords are stored in a plain text file called `passwords.txt` in the same directory as the program. The format is:
```
website,password
```

## Random Password Generation

Generated passwords are 12 characters long and include:
- Uppercase letters (A-Z)
- Lowercase letters (a-z)
- Numbers (0-9)
- Special characters (!@#$%^&*()-_=+)

## Security Considerations

⚠️ **Important Security Notes:**

- This is a **basic educational project** and should not be used for storing real, sensitive passwords
- Passwords are stored in **plain text** without encryption
- The storage file is not protected and can be read by anyone with access to your computer
- For real password management, use established solutions like:
  - Bitwarden
  - 1Password
  - KeePass
  - LastPass

## Potential Improvements

If you'd like to enhance this project, consider:
- Encrypting the password storage file
- Adding a master password to protect the application
- Implementing password strength indicators
- Adding search functionality
- Creating a more modern GUI
- Adding password expiration reminders
- Implementing secure password copying to clipboard

## License

This is an educational project. Feel free to use and modify as needed.

## Author

Created by BinaM122
