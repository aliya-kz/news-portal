package org.zhumagulova.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhumagulova.dao.NewsRepo;
import org.zhumagulova.dao.NewsDuplicateRepo;
import org.zhumagulova.models.Language;
import org.zhumagulova.models.News;
import org.zhumagulova.models.NewsDuplicate;


import java.util.Arrays;
import java.util.List;



@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LoggerFactory.getLogger("NewsServiceImpl");

    private final NewsRepo newsRepo;

    private final NewsDuplicateRepo newsDuplicateRepo;

    private final LanguageService languageService;

    @Autowired
    public NewsServiceImpl(NewsRepo localizedNewsRepo, NewsDuplicateRepo newsRepo, LanguageService languageService) {
        this.newsRepo = localizedNewsRepo;
        this.newsDuplicateRepo = newsRepo;
        this.languageService = languageService;
    }

    @Override
    @Transactional
    public List<News> getAllNews() {
        long languageId = languageService.getLanguageIdByLocale();
        return newsRepo.getAllNews(languageId);
    }

    @Override
    @Transactional
    public News getNewsById(long id) {
        return newsRepo.getNewsById(id);
    }

//TODO
    @Override
    @Transactional
    public long createNews(News news, long sourceNewsId) {
        Language language = languageService.getLanguageByLocale();
        long id = newsRepo.createNews(news, language);

        //add checking if news with that language exist

        //check if there is newsDuplicate with source id = sourceNewsId and dupl_id=null

            NewsDuplicate newsDuplicate = new NewsDuplicate();
            newsDuplicate.setSourceNews(news);
            if (sourceNewsId != 0) {
                News sourceNews = newsRepo.getNewsById(sourceNewsId);
                newsDuplicate.setDuplicatedNews(sourceNews);

                NewsDuplicate newsDuplicate1 = new NewsDuplicate();
                newsDuplicate1.setDuplicatedNews(news);
                newsDuplicate1.setSourceNews(sourceNews);
                newsDuplicateRepo.createNewsDuplicate(newsDuplicate1);
        }
            newsDuplicateRepo.createNewsDuplicate(newsDuplicate);
        return -1;
    }


    @Override
    @Transactional
    public void updateNews(News news, long id) {
        newsRepo.updateNews(news);
    }

    @Override
    @Transactional
    public void delete(long id) {
        newsDuplicateRepo.deleteNewsDuplicate(id);
        newsDuplicateRepo.deleteNewsSource(id);
        newsRepo.deleteNews(id);
    }

    @Override
    @Transactional
    public void deleteSeveral(String[] ids) {
        Arrays.stream(ids).map(Long::parseLong)
                .forEach(id -> newsRepo.deleteNews(id));
    }

    @Transactional
    @Override
    public News getNewsDuplicate(long id, long langId) {
        List<NewsDuplicate> duplicates = newsDuplicateRepo.getDuplicates(id);
        if (duplicates == null || (duplicates.size() == 1 && duplicates.get(0).getDuplicatedNews() == null)) {
            return newsRepo.getNewsById(id);
        } else {
            return duplicates.stream().
                    filter( dupl -> dupl.getDuplicatedNews().getLanguage().getId() == langId)
                    .map(NewsDuplicate::getDuplicatedNews).findFirst().get();
        }
    }
}
