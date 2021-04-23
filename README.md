Install Oracle 12c

Install Eclipse IDE

Data Source:
------------
1) CSV file stored as "IEEE_Data"
2) BibTex file stored as "dblp_Data"
3) RIS file stored as "ScienceDirect_Data.ris"

Order of execution:
-------------------
1. CreateTable.java---------------------(Creates 3 tables with names CSV_IEEE,BIBTEX_DBLP,RIS_SCIENCE_DIRECT)
2. CSV_Apache_POI.java------------------(Wrapper class to convert csv file to relational database where it reads IEEE_Data and inserts values into CSV_IEEE table)
3. BibTex_dblp.java---------------------(Wrapper class to convert bib file to relational database where it reads dblp_Data and inserts values into BIBTEX_DBLP table)
4. RIS.java-----------------------------(Wrapper class to convert ris file to relational database where it reads ScienceDirect_Data.ris and inserts values into RIS_SCIENCE_DIRECT table)
5. Merge_and_Duplicates_Removal.java----(Mediator class to merge 3 tables into one named "MERGER", and identify duplicates and delete all such rows)
6. Out_CSV_Generation.java--------------(User query if they need output in csv format)
7. Out_Bib_Generation.java--------------(User query if they need output in bib format)
8. Out_RIS_Generation.java--------------(User query if they need output in ris format)
