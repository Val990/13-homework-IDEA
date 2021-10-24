package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;

@AllArgsConstructor
@Data
public class ProductManager {
    private ProductRepository items;


    public void add(Product item) {
        items.save(item);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product item : items.getAll()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product item, String search) {
        if (item instanceof Book) {
            Book book = (Book) item;
            if (book.getAuthor().contains(search)) {
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }
            return false;
        }
        if (item instanceof TShirt) {
            TShirt smartphone = (TShirt) item;
            if (smartphone.getManufacturer().contains(search)) {
                return true;
            }
            if (smartphone.getName().contains(search)) {
                return true;
            }
            return false;
        }
        return false;
    }
}