package com.niit.qlsv.dao.customRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CustomRepository<T> {
    /**
     * pagination với dữ liệu trong câu truy vấn.
     *
     * @param pageable đối tượng phân trang org.springframework.data.domain.Pageable
     * @param query    Câu truy vấn
     * @param alias    alias của persistence class sau mệnh đề FROM eg: 'select s from EntBase s' thì 's' là alias
     * @param params   danh sách tham số trong câu truy vấn dạng key, value
     * @return Page danh sách thực thể kiểu dữ liệu T
     */
    Page<T> paginator(Pageable pageable, String query, String alias, Map<String, Object> params);
}
