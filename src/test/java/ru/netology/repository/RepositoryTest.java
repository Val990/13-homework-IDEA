package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    ProductRepository repo = new ProductRepository();

    private Book book1 = new Book(1, "Jane Eyre", 600, "Bronte");
    private Book book2 = new Book(2, "Three Men in a Boat", 700, "Jerome");
    private Book book3 = new Book(3, "Iphone", 1_000, "Poug");
    private TShirt tShirt1 = new TShirt(4, "TShirt1", 2_000, "Nike");
    private TShirt tShirt2 = new TShirt(5, "TShirt2", 1_500, "Demix");
    private TShirt tShirt3 = new TShirt(6, "TShirt3", 1_000, "Cropp");

    @BeforeEach
    public void setUp() {
        
        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(tShirt1);
        repo.save(tShirt2);
        repo.save(tShirt3);
    }

    @Test
    public void shouldRemoveByExistElement() {
        Product[] actual = repo.removeById(1);
        Product[] expected = new Product[] {book2, book3, tShirt1, tShirt2, tShirt3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByNotExistElement() {

        assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }
}