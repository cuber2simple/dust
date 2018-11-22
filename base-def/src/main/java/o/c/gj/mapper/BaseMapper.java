package o.c.gj.mapper;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * T 是基本对象类型， E是主键类型
 *
 * @param <T>
 * @param <E>
 */
public interface BaseMapper<T, E> {
    /**
     * 按照主键删除
     *
     * @param pKey
     * @return
     */
    int deleteByPKey(E pKey);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 按照主键查找
     *
     * @param pKey
     * @return
     */
    T selectByPKey(E pKey);

    /**
     * 根据传入的值更新,只要不为空就更新
     *
     * @param record
     * @return
     */
    int updateDynamic(T record);

    /**
     * 根据主键更新全部
     *
     * @param record
     * @return
     */
    int updateWhole(T record);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    Page<T> pageQuery(Map map);
}
