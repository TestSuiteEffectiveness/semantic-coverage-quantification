'''1.  RMS, Stement, branch
2. branch, line, RMS'''
def loadTests(F):
    data = {}
    # Open file using 'with' statement to ensure proper closure
    with open(F, 'r') as file:
        lines = file.readlines()
        for line in lines:
            # Strip and split the line into test suite and tests
            line = line.strip().split(":")
            test_suite = line[0]
            tests = list(map(int, line[1].split(',')))  # Convert test cases to integers
            data[test_suite] = tests
    return data




def RS(RMS, STMTS):
    # List to store the inclusion relationships
    inclusions = []
    
    # Extract keys (assuming all dictionaries have the same keys)
    keys = list(RMS.keys())
    
    # Loop for i and j from  1to 20 (or from 0 to the length of the keys)
    for i in range(len(keys)):
        for j in range(len(keys)):
            # Check if i and j are different to avoid self-comparison
            if i != j:
                key_i = keys[i]
                key_j = keys[j]
                
                
                # Perform the comparison based on RMS, STAT
                if (not(RMS[key_i][0] ==RMS[key_j][0] and 
                    STMTS[key_i][0]==STMTS[key_j][0])and
                    (RMS[key_i][0] >=RMS[key_j][0] and 
                    STMTS[key_i][0] >= STMTS[key_j][0])):
                    # Append the relationship to the inclusions list
                    inclusions.append(f'"{key_j}" -> "{key_i}"')

    return inclusions

###############################

def compare_dictionaries(LINE, BRANCH, STMTS):
    # List to store the inclusion relationships
    inclusions = []
    
    # Extract keys (assuming all dictionaries have the same keys)
    keys = list(LINE.keys())
    
    # Loop for i and j from  1to 20 (or from 0 to the length of the keys)
    for i in range(len(keys)):
        for j in range(len(keys)):
            # Check if i and j are different to avoid self-comparison
            if i != j:
                key_i = keys[i]
                key_j = keys[j]
                
                
                # Perform the comparison based on LINE, BRANCH, and STMTS dictionaries
                if (not(LINE[key_i][0] ==LINE[key_j][0] and 
                    BRANCH[key_i][0]==BRANCH[key_j][0] and 
                    STMTS[key_i][0] ==STMTS[key_j][0])and
                    (LINE[key_i][0] >=LINE[key_j][0] and 
                    BRANCH[key_i][0] >= BRANCH[key_j][0] and 
                    STMTS[key_i][0] >= STMTS[key_j][0])):
                    # Append the relationship to the inclusions list
                    inclusions.append(f'"{key_j}" -> "{key_i}"')

    return inclusions

# Load the test data from files
''' for testing
RMS=loadTests('R.txt')
line=loadTests('l.txt')
b=loadTests('b.txt')
'''
LINE = loadTests('line.txt')
BRANCH = loadTests('branch.txt')
STMTS = loadTests('stmt.txt')
RMS= loadTests('RMS.txt')

# Call the function to compare dictionaries
'''
inclusions_result_RMS_Stement_branch = compare_dictionaries(RMS, line, b)
'''
SLB = compare_dictionaries(STMTS, LINE, BRANCH)
RSL= compare_dictionaries(RMS, STMTS, LINE)
RS_result=RS(RMS,STMTS)
def write_results_to_file(filename, inclusions):
    # Open the file in write mode ('w') to create/overwrite the file
    with open(filename, 'w') as file:
        # Write each inclusion from the list to the file, one per line
        for inclusion in inclusions:
            file.write(inclusion + '\n')  # Add a newline after each entry





# Write the results to a file named 'inclusions.txt'
#write_results_to_file('test.txt', inclusions_result_RMS_Stement_branch)

write_results_to_file('SLB.txt', SLB)
write_results_to_file('RSL.txt', RSL)
write_results_to_file('RS.txt', RS_result)


# Output the results to the console (optional)
#print(inclusions_result)



# Output the results
#print(inclusions_result)
