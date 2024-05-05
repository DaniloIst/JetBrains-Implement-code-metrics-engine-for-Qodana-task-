# Complexity Analyzer Program

## Description

* This project aims to provide a comprehensive static code analysis tool specifically designed for Kotlin projects. It leverages algorithms to detect code smells, enforce coding standards, and ensure high-quality Kotlin code.

## Overview

* The Complexity Analyzer Program is made in Java the application is designed to analyze the complexity of given Kotlin files,
by that I mean it counts the number of named keywords after the program is done it will output the names
of the given files, the three most complex functions(functions with the biggest complexity score).

## The logic behind the app

 * We try to imitate the compiler or an interpreter, their first step is scanning the code so we take raw source code from each Kotlin file as a series of characters and groups which we call tokens. These are "words" and "punctuation" that make up a programming language's grammar.
   
 * To step of scanning a Kotlin file is very easy, we first make a while loop which checks if we are at the end of a file in that while loop we call up a function that has a switch statement that checks for types of characters for example if we find an `(` we know that by itself it does nothing so we just add it to our token list.
   
 * There are different types of tokens we will group them into different categories:
   * First is Single-Character Tokens like ( or [ or even +
   * Second is  One-Or-Two Character Tokens for example they can be = or >= they are mostly operators for any type of equality.
   * Third are Liters they are groups that define our code like IDENTIFIERs which are names of functions, variables, etc. There are also STRING and NUMBER.
   * Fourth are keywords like CLASS, FOR, VAR, etc. which we know are reserved and already declared.
   * EOF - End Of File
     
 * How do we know which type of token we found if the current character is not a case then by default we check if it is a NUMBER, if it is we add a NUMBER token with its value. For STRING it's easier if we find a `"` we know that means we have a string then we just advance while adding all the characters till we find the next `"` after that we know we are at the end of a STRING. How do we check if we have an IDENTIFIER we know they can't be numbers so after we check for that we call a function that will check if it's a keyword in this case we want to know if we are in a function so we first check if a character is isAlpha and AlphaNumeric if it is we advance till we have a complete word now we create a token with that word and check if its a keyword named FUN if it is we save the name and we continue the process while counting other keywords we need to know in this case loops and if statement till we hit another FUN now we create an Object that will save our function and its complexity and we repeat the whole process.

 * After we save every object in a list we will just sort the list by its complexity and print the top three results.

 * For the CamelCase using a for loop we will go through the list checking the names of the functions if it abides by the rules we just continue with our loop if it doesn't we increment our counter of nonCompliantMethods and print the name of the function
 after we finish with the loop we will get the percentage of nonCompliantMethods by dividing the number of nonCompliantMethods by the size of the list * 100;

*** 
## Installation and Running the Program(You should have java installed on your machine)

1. Clone the Repository: Clone the repository to your local machine using Git(Or Download the zip file of the repository).
   
2. Navigate to the Project Directory: Change into the project directory you can put all of your Kotlin files in the codebases directory.

3. Open the project directory in Command Prompt(Windows)/Terminal(Linux) depends of your operating system.

4. To run the code first compile it using the command: ```javac Main.java```, then use the command: ```javac Main.java codebases```


