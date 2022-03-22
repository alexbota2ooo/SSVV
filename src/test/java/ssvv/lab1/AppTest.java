package ssvv.lab1;

import domain.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    static Service service;

    @BeforeClass
    public static void initialize() {
        StudentValidator studentValidator = new StudentValidator();
        String studenti = "fisiere/Studenti.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(studenti);

        // Declared for service constructor
        String teme = "fisiere/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(teme);
        String note = "fisiere/Note.xml";
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(note);

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @AfterClass
    public static void clearRepo() {
        service.deleteStudent("999");
    }

    @Test
    public void testAddStudent1() {
        Student student = new Student("999", "boro", 931, "boro@yahoo.com");
        assertNull(service.addStudent(student));
    }


    @Test
    public void testAddStudent2() {
        // Duplicate
        Student student = new Student("999", "boro", 931, "boro@yahoo.com");
        assertNotNull(service.addStudent(student));
    }

}
