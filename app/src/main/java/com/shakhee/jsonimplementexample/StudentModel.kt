package com.shakhee.jsonimplementexample

class StudentModel{

   lateinit  var rollNumber: String
   lateinit  var name: String
   lateinit var classes: String
   lateinit var section: String
   lateinit var gender: String
   lateinit var totalMarks: String

   constructor(
      rollNumber: String,
      name: String,
      classes: String,
      section: String,
      gender: String,
      totalMarks: String
   ) {
      this.rollNumber = rollNumber
      this.name = name
      this.classes = classes
      this.section = section
      this.gender = gender
      this.totalMarks = totalMarks
   }

   constructor()
}
