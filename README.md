# introduction-to-microservices

What to do
In this module you will need to create base structure of microservices system.
During this task you need to implement the next two services:

* Resource Service
* Song Service

## Sub-task 1: Resource Service
For a Resource Service, it is recommended to implement a service with CRUD operations for processing mp3 files.

When uploading a mp3 file, the Resource Service should process the file in this way:
Extract file metadata. An external library can be used for this purpose.(e.g. Apache Tika).
Store mp3 file to the underlying database of the service as Blob.
Invoke Song Service to save mp3 file metadata.


## Sub-task 2: Song Service
For the Song Service, it is rec
