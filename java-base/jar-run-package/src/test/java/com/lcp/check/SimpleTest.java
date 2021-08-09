package com.lcp.check;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2021/1/15-11:13
 */
public class SimpleTest {

  private final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

  @Test
  public void test12() {

    var aa = "agnoster cbjohnson eclm  gentoo  kawasaki nelsonjchen random  simplevi trout aight  chain  edan  gianu  "
        + "krisleech neolambda randomrussel slacker  tweetjay  clearance eden  gitstatus l  numist  redfish  "
        + "slavic-cat uggedal barracuda cmorrell emoji-powerline gnuykeaj lambda  ocean  red-snapper solarfish will  "
        + "coffeeandcode es  godfather lavender one  rider  spacefish wolf-theme beloglazov cor  fishbone graystatus "
        + "lolfish  pastfish robbyrussell sushi  yimmy bira  cyan  fishface harleen  mars  perryh  sashimi  syl20bnr "
        + "zeit bobthefish dangerous fishy-drupal idan  mish  pie  scorphish taktoa  zephyr bongnoster default  fisk  "
        + "integral mokou  plain  separation technopagan zish boxfish  dmorrell flash  jacaetevha mtahmed  pure  "
        + "shellder toaster budspencer doughsay fox  johanson nai  pygmalion simple-ass-prompt tomita ";

    var array = aa.split(" ");

    logger.info("{}", Arrays.toString(array));

    var list = Arrays.stream(array)
        .filter(item -> item.trim().length() > 0)
        .peek(item ->
                // System.out.println("omf theme " + item)
                System.out.println("omf install " + item)
            // logger.info("omf theme {}", item)
        )
        .collect(Collectors.toList());

    logger.info("{}", list);

  }

  @Test
  public void test49() {

    //language=JSON
    var aa = "{\"name\":\"lcp\",\"age\":89,\"address\": \"usa\"}";

    //language=Shell Script
    var bb = "echo `ps aux`";
  }

  @Test
  public void test60() {

    int aa = 9876543;
    Integer bb = 9876543;
    int hashCode = Integer.hashCode(aa);
    int div = hashCode % 100;
    logger.info("int.hashcode:{}", hashCode);
    logger.info("int.div:{}", div);
  }

  @Test
  public void test71() {

    float aa1 = 0.33F / 0.1F;
    double aa2 = 0.3333333 / 0.1;
    logger.info("{}", aa1);
    logger.info("{}", aa2);
  }

  @Test
  public void test80() {
    System.out.println("0xCAFEBABE & 0x0FFFFFFFFL：" + (0xCAFEBABE & 0x0FFFFFFFFL));
  }

  @Test
  public void test85() {

    var name = System.getProperty("os.name");
    System.out.println("name = " + name);

  }

  @Test
  public void test93() {

    var aa = new Object();
    int address = System.identityHashCode(aa);
    System.out.println("address = " + address);
    System.out.printf("%02x\n", address);

  }

  @Test
  public void test103() {
    double a = Math.pow(2, 32);
    System.out.println("a = " + a);

  }
}
