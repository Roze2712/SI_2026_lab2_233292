package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SI2026Lab2Test {

    private Library library = new Library();

    @Test
    void searchBookEveryStatementTest() {
        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));
        library.addBook(new Book("Clean Code", "Martin", "IT"));
        assertNotNull(library.searchBookByTitle("Clean Code"));
    }

    @Test
    void borrowBookEveryBranchTest() {
        library.addBook(new Book("The Hobbit", "Tolkien", "Fantasy"));
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", "Tolkien"));
        library.borrowBook("The Hobbit", "Tolkien");
        assertThrows(RuntimeException.class, () -> library.borrowBook("The Hobbit", "Tolkien"));
    }

    @Test
    void returnBookEveryPathTest() {
        // Path 1: Празен наслов (IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> library.returnBook(""));

        // Path 2: Книгата не постои во листата (RuntimeException - Book not found)
        assertThrows(RuntimeException.class, () -> library.returnBook("NonExistent"));

        // Подготовка за следните патеки
        library.addBook(new Book("Clean Code", "Martin", "IT"));
        
        // Path 3: Книгата постои но НЕ е изнајмена (RuntimeException - Book was not borrowed)
        assertThrows(RuntimeException.class, () -> library.returnBook("Clean Code"));

        // Path 4: Книгата постои и Е успешно вратена
        library.borrowBook("Clean Code", "Martin"); // Прво ја изнајмуваме
        library.returnBook("Clean Code"); // Сега ја враќаме
        // Проверка: дали сега е достапна?
        assertNotNull(library.searchBookByTitle("Clean Code"));
    }
}