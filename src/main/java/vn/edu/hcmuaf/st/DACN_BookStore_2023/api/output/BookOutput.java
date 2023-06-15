package vn.edu.hcmuaf.st.DACN_BookStore_2023.api.output;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class BookOutput {
    private int page;
    private int totalPage;
    private List<BookDTO> result = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<BookDTO> getResult() {
        return result;
    }

    public void setResult(List<BookDTO> result) {
        this.result = result;
    }
}

