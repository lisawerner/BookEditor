# Book Editor

Book Editor is an application which helps the user to write fictive stories by organising content like character and place sheets as well as timeline development.
Content must not be handled in different files anymore. Character sheets for example can be created inside the editor and then pinned to text sections to set them in relationship.
The timeline gives an overview about the content per section.

Planning a fictive story is now a breeze.

# Installation

Coming soon

# Technology

The Book Editor is written in Java.
The user interface is made by Swing and AWT.
The book objects are saved as json files by using gson export and import. For that the project is set up as maven project.

# File Manifest

You can find the object structure in the following folders:
* [Book Structure](https://github.com/lisawerner/BookEditor/tree/master/src/book) with text sections
* [Character Sheets and Relationships](https://github.com/lisawerner/BookEditor/tree/master/src/person) 
* [World and places](https://github.com/lisawerner/BookEditor/tree/master/src/world)
* [Calendar and Section Timestamps](https://github.com/lisawerner/BookEditor/tree/master/src/time)

Code about the user interface is in the following folders:
* [JFrame with Pages](https://github.com/lisawerner/BookEditor/tree/master/src/GUI)
* [GUI Components](https://github.com/lisawerner/BookEditor/tree/master/src/GUI_components) for creating the design of the pages existing in Swing and AWT components were edited and combined

# Development Status Log

For more information about the development status view commits on branch workstation

* 11.10.2019: Beta Version is uploaded to master branche
