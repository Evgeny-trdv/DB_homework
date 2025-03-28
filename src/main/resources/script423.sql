SELECT student.name, student.age, faculty.name, faculty.color
FROM student
INNER JOIN faculty on faculty.id = student.faculty_id;

SELECT avatar.id, avatar.file_path, student.name, student.age
FROM avatar
INNER JOIN student on student.id = avatar.student_id;

