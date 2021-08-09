import static java.nio.charset.StandardCharsets.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

/**
 * desc:    <br/>
 * <p>
 * auth:    lichunpeng<br/>
 * <p>
 * time:    2018/1/15-17:02<br/>
 */
public class ByteBufTest {

  @Test
  public void test11() {

    //        ByteBuf byteBuf = Unpooled.directBuffer();
    ByteBuf byteBuf = Unpooled.buffer(512);
    System.out.println("byteBuf = " + byteBuf.capacity());
    System.out.println("byteBuf = " + byteBuf.maxCapacity());

    byte[] data = "abcdefg".getBytes();
    byte[] data2 = new byte[data.length];

    byteBuf.writeBytes(data).readBytes(data2, 0, data.length);

    String value = new String(data2);
    System.out.println("value = " + value);
  }

  @Test
  public void test33() {

    System.out.println(Integer.MAX_VALUE);
  }

  @Test
  public void test39() {

    //创建一个16字节的buffer,这里默认是创建heap buffer
    ByteBuf buf = Unpooled.buffer(16);
    //写数据到buffer
    for (int i = 0; i < 16; i++) {
      buf.writeByte(i + 1);
    }
    //读数据
    for (int i = 0; i < buf.capacity(); i++) {
      //            System.out.print(buf.getByte(i) + ", ");
    }

    String value = (String) buf.getCharSequence(0, 16, Charset.defaultCharset());
    System.out.println("buf.isReadable() = " + buf.isReadable());
    System.out.println("value = " + value + "=");
  }

  @Test
  public void test60() {

    CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

  }

  @Test
  public void test68() {

    ByteBuf byteBuf = Unpooled.buffer(300);
    show(byteBuf);

    byteBuf.writeByte(23);
    String value =
        "{\"employees\":[{\"firstName\":\"Bill\",\"lastName\":\"Gates\"},{\"firstName\":\"George\"," +
            "\"lastName\":\"Bush\"},{\"firstName\":\"Thomas\",\"lastName\":\"Carter\"}]}";
    byteBuf.writeBytes(value.getBytes());
    show(byteBuf);

    byte[] array = byteBuf.array();
    System.out.println("aray = " + array.length);

    ByteBuf head = byteBuf.readBytes(1);
    byteBuf.markReaderIndex();
    ByteBuf body = Unpooled.buffer(byteBuf.readableBytes());
    byteBuf.readBytes(body);

    byte procNumber = head.readByte();
    System.out.println("head = " + procNumber);
    System.out.println("body = " + body.toString(UTF_8));

    byteBuf.markReaderIndex();
    show(byteBuf);

    byteBuf.release();

  }

  private void show(ByteBuf byteBuf) {
    System.out.println("readable = " + byteBuf.readableBytes());
    System.out.println("writable = " + byteBuf.writableBytes());
  }

  @Test
  public void test105() {

    ByteBuf byteBuf = Unpooled.copiedBuffer("1234567890".getBytes());

    ByteBuf a = byteBuf.copy(0, 4);
    ByteBuf b = byteBuf.copy(4, 2);

    String a1 = a.readBytes(a.readableBytes()).toString(Charset.forName("UTF-8"));
    String b1 = b.readBytes(b.readableBytes()).toString(Charset.forName("UTF-8"));

    System.out.println("a1 = " + a1);
    System.out.println("b1 = " + b1);

  }

  @Test
  public void test122() {

    var source = "请问阿斯顿支持下";

    var pooledByteBufAllocator = new PooledByteBufAllocator(true);
    var byteBuf = pooledByteBufAllocator.heapBuffer(source.length() + 10);
    byteBuf.writeBytes((source + "_check").getBytes());
    byte[] result = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(result);
    var str = new String(result);
    System.out.println("str = " + str);

  }

  @Test
  public void test139() {

    var eventExecutorGroup = new DefaultEventExecutorGroup(10);
    var eventExecutor = eventExecutorGroup.next();

  }
}
