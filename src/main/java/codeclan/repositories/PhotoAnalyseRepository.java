package codeclan.repositories;

import codeclan.models.PhotoAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoAnalyseRepository extends JpaRepository<PhotoAnalyse,Long>{
    @Query("SELECT count(a) FROM PhotoAnalyse a")
     double findAllRecords();

   @Query("SELECT count(gender) FROM PhotoAnalyse where gender='male'")
   double findAllMen();

    @Query("SELECT count(gender) FROM PhotoAnalyse where gender='female'" )
    double findAllWomen();

    @Query("SELECT count(age) FROM PhotoAnalyse where age>=10 and age<=30" )
    double findAge1030();

    @Query("SELECT count(age) FROM PhotoAnalyse where age>=30 and age<=50" )
    double findAge3050();

    @Query("SELECT count(age) FROM PhotoAnalyse where age>=50 and age<=70" )
    double findAge5070();

    @Query("SELECT count(age) FROM PhotoAnalyse where age>=70" )
    double findAge70plus();

    @Query("SELECT count(anger) FROM PhotoAnalyse where anger>=0.7" )
    double findAnger();

    @Query("SELECT count(contempt) FROM PhotoAnalyse where contempt>=0.7" )
    double findContempt();

    @Query("SELECT count(disgust) FROM PhotoAnalyse where disgust>=0.7" )
    double findDisgust();

    @Query("SELECT count(fear) FROM PhotoAnalyse where fear>=0.7" )
    double findFear();

    @Query("SELECT count(happiness) FROM PhotoAnalyse where happiness>=0.7" )
    double findHappiness();

    @Query("SELECT count(neutral) FROM PhotoAnalyse where neutral>=0.7" )
    double findNeutral();

    @Query("SELECT count(sadness) FROM PhotoAnalyse where sadness>=0.7" )
    double findSadness();

    @Query("SELECT count(surprise) FROM PhotoAnalyse where surprise>=0.7" )
    double findSurprise();
}
