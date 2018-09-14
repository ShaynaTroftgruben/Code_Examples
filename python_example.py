import re
import pandas as pd
#Question 1
files = ["Sample_Acute_Clinical_Note.txt", "Sample_Chronic_Clinical_Note.txt"]
for file in files:
 fh = open(file, 'r')
 fulltext = fh.read().strip()
 matches = re.search(r'([A-Z][a-z]+\s+[A-Z]\.\s+[A-Z][a-z]+)', fulltext)
 print "Name:   ", matches.group(0) 
 
 dateRE= re.search(r'(\d+/+\d+/+[0-9]+[0-9]+[0-9]+[0-9])', fulltext)#finds all strings of the pattern digit followed by / followed by another didgit / then a string that is four numbers long 
 print "Date:   ", dateRE.group(0)
 
 headerRE = re.findall(r'(?:\b[A-Z]+\b\s*)+(?=[:])', fulltext)#finds all upercase words seperated by spaces ending in a semicolan
 print "header: ", headerRE
 
 bpRE = re.search(r'((blood pressure|BP)\s+[0-9]{3}/[0-9]{2})', fulltext)#finding the word blood pressure followed by a fraction
 print "Blood P:", bpRE.group(0)

 pulseRE = re.search(r'((pulse)\s[0-9]*)', fulltext)#finding the word pulse followed by a number
 print "Pulse: ", pulseRE.group(0)

 #Question 2
f = pd.read_csv("CAREGIVERS.CSV")

print f["LABEL"].nunique() #nunuique counts the unique valeus in the label column
print f["DESCRIPTION"].nunique()#nunuique counts the unique values in the description column
#Answer: 230,16

#Question 3:
print f["DESCRIPTION"].value_counts()["Attending"] #values_counts() counts all of the data fitting the given conditions
#Answer: 189

#Question 4
g = pd.read_csv("ADMISSIONS.CSV")
minAdmitDate = g.groupby("SUBJECT_ID")["ADMITTIME"].min() #creating a new table that groups all admition time by a individual patient, the min() function then finds the minumum of the admission dates
print minAdmitDate.head

#Question 5
patientsFile = pd.read_csv("PATIENTS.CSV")
minAdmitDate = minAdmitDate.reset_index()
mergedTable = pd.merge(patientsFile, minAdmitDate, on= "SUBJECT_ID") #merging the the table created in question 4 with the patients file
print mergedTable.head

#Question 6
mergedTable["DOB"] = pd.to_datetime(mergedTable["DOB"])
mergedTable["ADMITTIME"] = pd.to_datetime(mergedTable["ADMITTIME"])
mergedTable ["AGE"] = (mergedTable ["ADMITTIME"] - mergedTable ["DOB"]).dt.days/365 #finding the age for each patient and adding the column to the dataframe
print mergedTable ["AGE"]

#Question 7
a = mergedTable.query("SUBJECT_ID == 99403")['AGE'] #query is created that finds the age of a specific pateint using SUBJECT_ID
b = mergedTable.query("SUBJECT_ID == 19153")['AGE']
c = mergedTable.query("SUBJECT_ID == 27845")['AGE']
d = mergedTable.query("SUBJECT_ID == 50733")['AGE']
e = mergedTable.query("SUBJECT_ID == 41278")['AGE']

print a 
print b
print c
print d
print e

#Question 8
countOfAgeBetween = mergedTable[(mergedTable["AGE"] >= 15) & (mergedTable["AGE"] <= 20)].count()["AGE"] #counting all patients in the data range 
print countOfAgeBetween
#Answer 375

#Question 9 
#Because people over the age of 89 are typically easy to identify, patients in the MIMIC database that are over the age of 89 are given a fixed age of 300. 
#So because these people DOB are so far shifted when you do the calulation of Admittime - DOB it causes the patient age to be negative



