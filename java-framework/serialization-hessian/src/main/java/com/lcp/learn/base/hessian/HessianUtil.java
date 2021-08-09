package com.lcp.learn.base.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianUtil {

  public byte[] serialize(Object obj) throws IOException {
    if (obj == null) {
      throw new NullPointerException();
    }

    var byteArrayOutputStream = new ByteArrayOutputStream();
    var hessianOutput = new HessianOutput(byteArrayOutputStream);
    hessianOutput.writeObject(obj);
    return byteArrayOutputStream.toByteArray();
  }

  public Object deserialize(byte[] by) throws IOException {
    if (by == null) {
      throw new NullPointerException();
    }

    var byteArrayInputStream = new ByteArrayInputStream(by);
    var hessianInput = new HessianInput(byteArrayInputStream);
    return hessianInput.readObject();
  }

}
