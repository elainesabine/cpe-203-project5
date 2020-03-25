import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Account_Tests
{
    private static List<Account> all = new ArrayList<>();
    private static Account[] some = new Account[3];

    private static Account flaubert = new Account("Gustave", "Flaubert", 1003, 10000, true);
    //all of these other versions of "flaubert" differ from the
    //original in only one field
    private static Account flaubert2 = new Account("Guy", "Flaubert", 1003, 0, true);
    private static Account flaubert3 = new Account("Gustave", "de Maupassant", 1003, 0, true);
    private static Account flaubert4 = new Account("Gustave", "Flaubert", 2004, 500000, true);
    private static Account flaubert5 = new Account("Gustave", "Flaubert", 1003, 1, true);
    private static Account flaubert6 = new Account("Gustave", "Flaubert", 1003, 0, false);
    private static Account flaubert7 = new Account("Gustave", "Flaubert", 1003, 0, true);


  private static void setup_array(){
      some[0]=flaubert;
      some[1]=flaubert7;
      some[2]=flaubert4;
     }
    private static void setup_list(){
        all.clear();
        all.add(flaubert);
        all.add(flaubert2);
        all.add(flaubert3);
        all.add(flaubert4);
        all.add(flaubert5);
        all.add(flaubert6);
        all.add(flaubert7);
    }

    @Test(timeout=1000)
    public void test_sort_all_naturalOrder()
    {
        setup_list();
       Collections.sort(all);
       List<Account> expectedResult = Arrays.asList(flaubert6,flaubert7,flaubert5,flaubert,flaubert2,flaubert3,flaubert4);
       assertEquals(all,expectedResult);

    }

    @Test(timeout=1000)
    public void test_sort_all_balance()
    {
        setup_array();
        Arrays.sort(some,new BalanceComparator());
        Account[]  expectedResult = {flaubert7,flaubert,flaubert4};
        for(int i=0;i<3;i++)
            assertEquals(expectedResult[i],some[i]);
    }

    @Test(timeout=1000)
    public void test_equals()
    {
       Account a = new Account("some", "one", 1003, 12000, true);
       Account b = new Account("some", "one", 1003, 12000, true);

        assertTrue(a.equals(b));
    }

    @Test(timeout=1000)
    public void test_hashCode()
    {
        Account a = new Account("some", "one", 1003, 12000, true);
        Account b = new Account("some", "one", 1003, 12000, true);
        assertEquals(a.hashCode() ,b.hashCode());
    }



}

