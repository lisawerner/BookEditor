# Book Editor

Book Editor is an application which helps the user to write fiction story by organising content like character and place sheets or timeline development.
Content must not be handled in different files anymore. Character sheets for example can created inside the editor and than pinned to text sections to set them in relationship.
The Timeline gives an overview about the content per section.

Planing a fictive story is now a breeze.

# Installation

Coming soon

# Technologie

The Book Editor is written in Java.
The user interface is made by Swing and AWT.
The book objects are saved as json file by using gson export and import. For that the project is set up as maven project.

# File Manifest

you can find object structure in following folders:
* [Book Structure](https://github.com/lisawerner/BookEditor/tree/master/src/book) with text sections
* [Character Sheets and Relationships](https://github.com/lisawerner/BookEditor/tree/master/src/person) 
* [World and places](https://github.com/lisawerner/BookEditor/tree/master/src/world)
* [Calendar and Section Timestamps](https://github.com/lisawerner/BookEditor/tree/master/src/time)

code about the user interface are in following folders:
* [JFrame with Pages](https://github.com/lisawerner/BookEditor/tree/master/src/GUI)
* [GUI Components](https://github.com/lisawerner/BookEditor/tree/master/src/GUI_components) for creating the design of the pages existing Swing and AWT components were edited and combined

# Development Status Log

Follow small steped development status on branch workstation

* 11.10.2019: Beta Version is uploaded to master branche
