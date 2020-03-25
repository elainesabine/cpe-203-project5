import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   public static final double DELTA = 0.00001;

   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator artistcomparator = new ArtistComparator();
      int result1 = artistcomparator.compare(songs[2], songs[1]);
      assertTrue(result1 < 0);

      int result2 = artistcomparator.compare(songs[3], songs[3]);
      assertTrue(result2 == 0);

      int result3 = artistcomparator.compare(songs[1], songs[5]);
      assertTrue(result3 > 0);

   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> TitleComparator = (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());

      Song[] songArr1 = new Song[]{songs[2], songs[1]};
      Arrays.sort(songArr1, TitleComparator);
      Song[] expected1 = new Song[]{songs[1], songs[2]};
      assertEquals(songArr1, expected1);

      Song[] songArr2 = new Song[]{songs[5], songs[7]};
      Arrays.sort(songArr2, TitleComparator);
      Song[] expected2 = new Song[]{songs[5], songs[7]};
      assertEquals(songArr2, expected2);

      Song[] songArr3 = new Song[]{songs[3], songs[4]};
      Arrays.sort(songArr3, TitleComparator);
      Song[] expected3 = new Song[]{songs[3], songs[4]};
      assertEquals(songArr3, expected3);
   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> YearComparator = Comparator.comparing(Song::getYear, (o1, o2) -> o2.compareTo(o1));
      Song[] songArr1 = new Song[]{songs[1], songs[2]};
      Arrays.sort(songArr1, YearComparator);
      Song[] expected1 = new Song[]{songs[2], songs[1]};
      assertEquals(songArr1, expected1);

      Song[] songArr2 = new Song[]{songs[5], songs[7]};
      Arrays.sort(songArr2, YearComparator);
      Song[] expected2 = new Song[]{songs[5], songs[7]};
      assertEquals(songArr2, expected2);

      Song[] songArr3 = new Song[]{songs[3], songs[4]};
      Arrays.sort(songArr3, YearComparator);
      Song[] expected3 = new Song[]{songs[4], songs[3]};
      assertEquals(songArr3, expected3);

   }

   @Test
   public void testComposedComparator()
   {
      Comparator<Song> c1 = Comparator.comparing(Song::getYear, (s1, s2)->s1.compareTo(s2));
      Comparator<Song> c2 = Comparator.comparing(Song::getArtist, (s1, s2)->s1.compareTo(s2));

      Comparator<Song> yearThenTitle = new ComposedComparator(c1, c2);
      Song[] songList3 = new Song[] {songs[0], songs[1]};
      Arrays.sort(songList3, yearThenTitle);
      Song[] expected3 = new Song[] {songs[1], songs[0]};
      assertEquals(expected3, songList3);


   }

   @Test
   public void testThenComparing()
   {
   }
//
//   @Test
//   public void runSort()
//   {
//      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
//      List<Song> expectedList = Arrays.asList(
//         new Song("Avett Brothers", "Talk on Indolence", 2006),
//         new Song("City and Colour", "Sleeping Sickness", 2007),
//         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
//         new Song("Foo Fighters", "Baker Street", 1997),
//         new Song("Gerry Rafferty", "Baker Street", 1978),
//         new Song("Gerry Rafferty", "Baker Street", 1998),
//         new Song("Queen", "Bohemian Rhapsody", 1975),
//         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
//         );
//
//      songList.sort(
//         // pass comparator here
//      );
//
//      assertEquals(songList, expectedList);
//   }
}
