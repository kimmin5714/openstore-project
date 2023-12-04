package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import com.ssafy.board.model.BoardDto;

@Mapper
@CacheNamespace(flushInterval = 60000, size=2048)
public interface BoardMapper {

	void writeArticle(BoardDto boardDto) throws SQLException;
	@Cacheable(value="articles")
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	@Cacheable(value="articleCount")
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;

}
