package mapper;

import org.apache.ibatis.annotations.Param;

public interface TextMapper {
    public void savetext(@Param("name") String name,@Param("content") String content);
}
