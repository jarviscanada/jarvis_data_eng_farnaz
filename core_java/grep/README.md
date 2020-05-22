# Introduction
The JavaGrep application is designed for pattern matching purposes.It searches recrusively through all the files in a directory for the lines that matches the provided regular expression. Lines with those expressions are written into a new file. <br />
This project is implemented by using Java 8's Stream APIs and Lambda Expressions.

# Usage
This program takes three arguements: <br />
-`Regex`: The desired pattern that the user wants to find matches <br />
_  `Roothpath`: The given directory that searching should be done in <br />
_ `outfile`: The file that the matched lines are written into it <br />
An Example of program arguements: <br />
`.*IllegalArgutmentException.* ./src /tmp/grep.out`

# Pseudocode
The pseudocode of `process` method: <br />
```matchedLines = []
   for file in listFilesRecursively(rootDir)
   	for line in readLines(file)
   		if containsPattern(line)
   			matchedLines.add(line)
   writeToFile(matchedLines)
```

# Performance Issue
Since this program reads files line by line and store each line in a list ,the file size plays vital role in this application. Therefore, this program works efficiently in only small files and might run slowly in processing huge file sizes. Considering JavaGrepLambda applications which support more efficient search algorithms for larger workload size, is suggested.   

# Improvement
1. Using parallel stream in reading lines. 
2. In case of huge files, providing a warning regarding the speed of application.
3. Providing the location of each output line. 
