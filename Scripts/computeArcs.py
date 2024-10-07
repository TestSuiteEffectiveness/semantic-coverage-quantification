import os
import pandas as pd  # For saving to Excel

# Function to read arcs from file and convert them into a set of tuples
def read_arcs(file_path):
    arcs = set()  # Using a set to avoid duplicate arcs
    with open(file_path, 'r') as file:
        for line in file:
            # Split line based on whitespace (tabs or spaces) and keep both elements as a tuple
            arc = tuple(line.strip().split())
            arcs.add(arc)  # Add arc to the set
    return arcs

# Function to calculate the number of arcs in each file, common arcs, and union of arcs
def compare_arcs(file1, file2):
    # Read arcs from the files
    arcs_file1 = read_arcs(file1)
    arcs_file2 = read_arcs(file2)

    # Number of arcs in each file
    num_arcs_file1 = len(arcs_file1)
    num_arcs_file2 = len(arcs_file2)

    # Find common arcs between the two files (intersection)
    common_arcs = arcs_file1 & arcs_file2
    num_common_arcs = len(common_arcs)

    # Find the union of arcs from both files
    union_arcs = arcs_file1 | arcs_file2
    num_union_arcs = len(union_arcs)

    # Return the comparison results for this pair
    return num_arcs_file1, num_arcs_file2, num_common_arcs, num_union_arcs

# Function to process multiple pairs of text files
def process_file_pairs(file_pairs, output_excel):
    results = []  # To store the results of comparisons for all pairs

    for file1, file2 in file_pairs:
        print(f"Comparing {file1} and {file2}...")

        # Run the compare_arcs function on each pair and get the result
        num_arcs_file1, num_arcs_file2, num_common_arcs, num_union_arcs = compare_arcs(file1, file2)
        
        # Store the result in the results list
        results.append((file1, file2, num_arcs_file1, num_arcs_file2, num_common_arcs, num_union_arcs))

        # Print results for this pair
        print(f"Number of arcs in {file1}: {num_arcs_file1}")
        print(f"Number of arcs in {file2}: {num_arcs_file2}")
        print(f"Number of common arcs: {num_common_arcs}")
        print(f"Number of arcs in the union: {num_union_arcs}")
        print("-" * 40)

    # Create a DataFrame from the results
    df = pd.DataFrame(results, columns=['Graph1', 'Graph2', 'Size1', 'Size2', 'Intersection', 'Union'])

    # Save the DataFrame to an Excel file
    df.to_excel(output_excel, index=False)
    print(f"Results saved to {output_excel}")

# Define your pairs of text files
file_pairs = [
    ("SLB.txt", "TR2.txt"),
    ("SLB.txt", "PR3_22.txt"),
    ("SLB.txt", "TR3_22.txt"),
    ("RSL.txt", "PR1.txt"),
    ("RSL.txt", "TR1.txt"),
    ("RSL.txt", "PR2.txt"),
    ("RSL.txt", "TR2.txt"),
    ("RSL.txt", "PR3_22.txt"),
    ("RSL.txt", "TR3_22.txt"),
    ("PR1.txt", "TR1.txt"),
    ("PR1.txt", "PR2.txt"),
    ("PR1.txt", "TR2.txt"),
    ("PR1.txt", "PR3_22.txt"),
    ("PR1.txt", "TR3_22.txt"),
    ("TR1.txt", "PR2.txt"),
    ("TR1.txt", "TR2.txt"),
    ("TR1.txt", "PR3_22.txt"),
    ("TR1.txt", "TR3_22.txt"),
    ("PR2.txt", "TR2.txt"),
    ("PR2.txt", "PR3_22.txt"),
    ("PR2.txt", "TR3_22.txt"),
    ("TR2.txt", "PR3_22.txt"),
    ("TR2.txt", "TR3_22.txt"),
    ("PR3_22.txt", "TR3_22.txt"),
    ("PR1.txt","RS.txt"),
    ("TR1.txt","RS.txt"),
    ("PR2.txt","RS.txt"),
    ("TR2.txt","RS.txt"),
    ("PR3.txt","RS.txt"),
    ("TR3_22.txt","RS.txt")
]

# Output Excel file path
output_excel = "arc_comparison_results.xlsx"

# Run the comparison on all pairs of files and save to Excel
process_file_pairs(file_pairs, output_excel)
