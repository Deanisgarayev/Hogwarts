 select student.name, student.age, faculty.name, from student inner join faculty on student.id = faculty.student_id;
 select student.name, student.age, avatar.id from student inner join avatar on student.id = avatar.student_id;