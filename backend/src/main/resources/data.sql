INSERT INTO user_app (name, email, password) VALUES
  ('sa', 'sa@email.com', '$2a$10$yo9nRrztHF.58LZVIpnY0OD2A9CzIsZNQlXkMKyH9b9peLx4hDg7q');

INSERT INTO book (title, author, year_book, description, image_url, price) VALUES
('Clean Code', 
 'Robert Martin', 
  2008,
 'Noted software expert Robert C. Martin, presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship. Martin, who has helped bring agile principles from a practitioner s point of view to tens of thousands of programmers, has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code "on the fly” into a book that will instill within you the values of software craftsman, and make you a better programmer―but only if you work at it.',
 'https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCode.png',
 50),
 ('The Clean Coder', 
 'Robert Martin', 
  2011,
 'In The Clean Coder: A Code of Conduct for Professional Programmers, legendary software expert Robert C. Martin introduces the disciplines, techniques, tools, and practices of true software craftsmanship. This book is packed with practical advice - about everything from estimating and coding to refactoring and testing. It covers much more than technique: It is about attitude. Martin shows how to approach software development with honor, self-respect, and pride; work well and work clean; communicate and estimate faithfully; face difficult decisions with clarity and honesty; and understand that deep knowledge comes with a responsibility to act.',
 'https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCoder.png',
 50),
 ('Clean Architecture', 
 'Robert Martin', 
  2017,
 'Martins Clean Architecture doesn t merely present options. Drawing on over a half-century of experience in software environments of every imaginable type, Martin tells you what choices to make and why they are critical to your success. As you ve come to expect from Uncle Bob, this book is packed with direct, no-nonsense solutions for the real challenges you will face - the ones that will make or break your projects.',
 'https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanArchitecture.jpeg',
 50),
 ('Test Driven Development by Example', 
 'Kent Beck', 
  2003,
 'Quite simply, test-driven development is meant to eliminate fear in application development. While some fear is healthy (often viewed as a conscience that tells programmers to "be careful!"), the author believes that byproducts of fear include tentative, grumpy, and uncommunicative programmers who are unable to absorb constructive criticism. When programming teams buy into TDD, they immediately see positive results. They eliminate the fear involved in their jobs, and are better equipped to tackle the difficult challenges that face them. TDD eliminates tentative traits, it teaches programmers to communicate, and it encourages team members to seek out criticism However, even the author admits that grumpiness must be worked out individually! In short, the premise behind TDD is that code should be continually tested and refactored. Kent Beck teaches programmers by example, so they can painlessly and dramatically increase the quality of their work.',
 'https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_TDD.jpeg',
 50),
 ('Working Effectively With Legacy Code', 
 'Michael C. Feathers', 
  2004,
 'This book provides programmers with the ability to cost effectively handlecommon legacy code problems without having to go through the hugelyexpensive task of rewriting all existing code. It describes a series of practicalstrategies that developers can employ to bring their existing softwareapplications under control. The author provides useful guidance about how touse these strategies when refactoring or making functional changes to codebases. One of the book s key points is that it teaches developers to write teststhat can be used to make sure they are not unintentionally changing theapplication as they optimize it. Examples are provided in Java, C++, and Csharp,and the book assumes that the reader has some knowledge of UMLnotation. Strategies using UML and code in C++ and Java primarily whilelanguage independent advice will be delivered in side bars and appendices forlanguage specific users.',
 'https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_Refactoring.jpeg',
 50);
 
 INSERT INTO discount (num_books, discount) VALUES 
 (2, 5),
 (3, 10),
 (4, 20),
 (5, 25);
 