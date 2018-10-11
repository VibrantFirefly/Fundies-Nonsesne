import tester.*;

//to represent a list of XMLFrags
interface ILoXMLFrag { 
  //determines if two XML documents are the same 
  public boolean sameXMLDoc(ILoXMLFrag that);
  
  //is the ILoXMLFrag a MtLoXMLFRag?
  public boolean isMtLoXMLFrag();
  
  //is the ILoXMLFrag a ConsLoXMLFRag?
  public boolean isConsLoXMLFrag();
}

//empty list of XMLFrags
class MtLoXMLFrag implements ILoXMLFrag {
  //is the ILoXMLFrag a MtLoXMLFRag?
  public boolean isMtLoXMLFrag() {
    return true;
  }
  
  //is the ILoXMLFrag a ConsLoXMLFRag?
  public boolean isConsLoXMLFrag() {
    return false;
  }
  
  //determines if two ILoXMLFrags are the same
  public boolean sameXMLDoc(ILoXMLFrag that) {
    if (that.isMtLoXMLFrag()) {
      return true;
    }
    else {
      return false;
    }
    
  }
}


//non empty list of XMLFrags
class ConsLoXMLFrag implements ILoXMLFrag {
  IXMLFrag first;
  ILoXMLFrag rest;

  ConsLoXMLFrag(IXMLFrag first, ILoXMLFrag rest) {
    this.first = first;
    this.rest = rest;
  }
  
  //is the ILoXMLFrag a MtLoXMLFRag?
  public boolean isMtLoXMLFrag() {
    return false;
  }
  
  //is the ILoXMLFrag a ConsLoXMLFRag?
  public boolean isConsLoXMLFrag() {
    return true;
  }
  
  //determines if two ILoXMLFrags are the same
  public boolean sameXMLDoc(ILoXMLFrag that) {
    if (that.isConsLoXMLFrag()) {
      return this.sameConsLoXMLFrag((ConsLoXMLFrag)that);
    }
    else {
      return false;
    }
  }
  
  //determines if two ConsLoXMLFrags are the same
  public boolean sameConsLoXMLFrag(ConsLoXMLFrag that) {
    return this.first.sameXMLFrag(that.first)
        && this.rest.sameXMLDoc(that.rest);
  }
}

//to represent one XMLFrag
interface IXMLFrag {
  //determines if two XMLFrags have same name and 
  //the same attributes in the same order.
  public boolean sameXMLFrag(IXMLFrag that);
  
  //is the XMLFrag a Plaintext?
  public boolean isPlaintext();
  
  //is the XMLFrag a Tagged?
  public boolean isTagged();
}

// strings within XMLs
class Plaintext implements IXMLFrag {
  String txt;
  
  Plaintext(String txt) {
    this.txt = txt; 
  }
  
  //is the XMLFrag a plaintext?
  public boolean isPlaintext() {
    return true;
  }
  
  //is the XMLFrag a tagged?
  public boolean isTagged() {
    return false;
  }
  
  //determines if two XMLFrags have same name and 
  //the same attributes in the same order.
  public boolean sameXMLFrag(IXMLFrag that) {
    if (that.isPlaintext()) {
      return this.samePlaintext((Plaintext)that);
    }
    else {
      return false;
    }
  }
  
  //determines if two plaintexts are the same
  public boolean samePlaintext(Plaintext that) {
    return this.txt.equals(that.txt);
  }
  
}

// to represent taggeds
class Tagged implements IXMLFrag {
  Tag tag;
  ILoXMLFrag content;
  
  Tagged(Tag tag, ILoXMLFrag content) {
    this.tag = tag;
    this.content = content;
  }
  
  //is the XMLFrag a Plaintext?
  public boolean isPlaintext() {
    return false;
  }
  
  //is the XMLFrag a Tagged?
  public boolean isTagged() {
    return true;
  }
  
  //determines if two XMLFrags have same name and 
  //the same attributes in the same order.
  public boolean sameXMLFrag(IXMLFrag that) {
    if (that.isTagged()) {
      return this.sameTagged((Tagged)that);
    }
    else {
      return false;
    }
  }
  
  //determines if two taggeds are the same
  public boolean sameTagged(Tagged that) {
    return this.tag.sameTag(that.tag)
        && this.content.sameXMLDoc(that.content);
  }
}

// to represent tags
class Tag {
  String name;
  ILoAtt atts;
  
  Tag(String name, ILoAtt atts) {
    this.name = name;
    this.atts = atts;
  }
  
  // to determine if two tags are the same
  public boolean sameTag(Tag that) {
    return this.name.equals(that.name)
        && this.atts.sameLoAtt(that.atts);
  }
}

//to represent a list of attributes
interface ILoAtt {
  //is the ILoAtt a MtLoAtt?
  public boolean isMtLoAtt();
  
  //is the ILoAtt a ConsLoAtt?
  public boolean isConsLoAtt();
  
  //determines if two LoAtts are the same
  public boolean sameLoAtt(ILoAtt that);
}

//empty list of attributes 
class MtLoAtt implements ILoAtt {
  //is the ILoAtt a MtLoAtt?
  public boolean isMtLoAtt() {
    return true;
  }
  
  //is the ILoAtt a ConsLoAtt?
  public boolean isConsLoAtt() {
    return false;
  }
  
  //determines if both are MtLoAtt
  public boolean sameLoAtt(ILoAtt that) {
    if (that.isMtLoAtt()) {
      return true;
    }
    else {
      return false;
    }
  }
}

//non empty list of attributes
class ConsLoAtt implements ILoAtt {
  Att first;
  ILoAtt rest; 
  
  ConsLoAtt(Att first, ILoAtt rest) {
    this.first = first;
    this.rest = rest;
  }
  
  //is the ILoAtt a MtLoAtt?
  public boolean isMtLoAtt() {
    return false;
  }
  
  //is the ILoAtt a ConsLoAtt?
  public boolean isConsLoAtt() {
    return true;
  }
  
  //determines if both ILoAtt are the same ConsLoAtt
  public boolean sameLoAtt(ILoAtt that) {
    if (that.isConsLoAtt()) {
      return this.sameConsLoAtt((ConsLoAtt)that);
    }
    else {
      return false;
    }
  }
  
  //determines if two ConsLoAtt are the same
  public boolean sameConsLoAtt(ConsLoAtt that) {
    return this.first.sameAttribute(that.first)
        && this.rest.sameLoAtt(that.rest);
  }
}

//to represent one attribute
class Att {
  String name;
  String value;

  Att(String name, String value) {
    this.name = name;
    this.value = value;
  }
  
  //determines if two Attributes are the same
  public boolean sameAttribute(Att that) {
    return this.name.equals(that.name)
        && this.value.equals(that.value);
  }
  
}

// examples and tests
class ExamplesXML {
  //attributes
  Att volumeAtt1 = new Att("volume", "30db");
  Att volumeAtt2 = new Att("volume", "30db");
  Att durationAtt = new Att("duration", "5sec");
  
  //plaintexts
  Plaintext moth = new Plaintext("moth");
  Plaintext lamp = new Plaintext("lamp");
  
  // taggeds
  Tagged tagged1 = new Tagged(new Tag("yell", new MtLoAtt()), 
      new MtLoXMLFrag());
  Tagged tagged2 = new Tagged(new Tag("yell", new ConsLoAtt(this.volumeAtt1,  
      new ConsLoAtt(this.durationAtt, new MtLoAtt()))), new ConsLoXMLFrag(new Plaintext("I am "),
          new ConsLoXMLFrag(new Tagged(new Tag("yell", new MtLoAtt()), new ConsLoXMLFrag(new Tagged(new Tag("italic", new MtLoAtt()), 
          new ConsLoXMLFrag(new Plaintext("X"), new MtLoXMLFrag())), 
          new ConsLoXMLFrag(new Plaintext("ML"), new MtLoXMLFrag()))), 
          new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag()))));
  
  //tags
  Tag yellTag = new Tag("yell", new MtLoAtt());                             
  Tag yellTag2 = new Tag("yell", new ConsLoAtt(this.volumeAtt1,   
      new MtLoAtt()));
  Tag yellTag3 = new Tag("yell", new ConsLoAtt(this.volumeAtt1,  
      new ConsLoAtt(this.durationAtt, new MtLoAtt())));
  Tag italicTag = new Tag("italic", new MtLoAtt()); 
  
  //ILoAtts
  ILoAtt emptyLoatt = new MtLoAtt();
  ILoAtt volumeLoatt = new ConsLoAtt(new Att("volume", "30db"), new MtLoAtt());
  ILoAtt durationLoatt = new ConsLoAtt(new Att("duration", "5sec"), new MtLoAtt());
  ILoAtt bothLoatt1 = new ConsLoAtt(this.volumeAtt1, new ConsLoAtt(this.durationAtt, new MtLoAtt()));
  ILoAtt bothLoatt2 = new ConsLoAtt(this.durationAtt, new ConsLoAtt(this.volumeAtt1, new MtLoAtt()));
  
  //ConsLoAtts
  ConsLoAtt volumeLoatt2 = new ConsLoAtt(new Att("volume", "30db"), new MtLoAtt());
  ConsLoAtt durationLoatt2 = new ConsLoAtt(new Att("duration", "5sec"), new MtLoAtt());
  
  //MtLoAtt
  MtLoAtt emptyLoatt2 = new MtLoAtt();
  
  //XMLFrags
  IXMLFrag plaintext = new Plaintext("I am XML!");
  IXMLFrag tagged = new Tagged(this.yellTag, 
      new MtLoXMLFrag());
  IXMLFrag complex = new Tagged(this.yellTag2, 
      new ConsLoXMLFrag(plaintext, new MtLoXMLFrag()));
  IXMLFrag tagged3 = new Tagged(new Tag("yell", new ConsLoAtt(this.volumeAtt1,  
      new ConsLoAtt(this.durationAtt, new MtLoAtt()))), new ConsLoXMLFrag(new Plaintext("I am "),
          new ConsLoXMLFrag(new Tagged(this.yellTag, 
          new ConsLoXMLFrag(new Tagged(this.italicTag, 
          new ConsLoXMLFrag(new Plaintext("X"), new MtLoXMLFrag())), 
          new ConsLoXMLFrag(new Plaintext("ML"), new MtLoXMLFrag()))), 
          new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag()))));
  IXMLFrag tagged4 = new Tagged(new Tag("yell", new ConsLoAtt(this.volumeAtt1,  
      new ConsLoAtt(this.durationAtt, new MtLoAtt()))), new ConsLoXMLFrag(new Plaintext("I am "),
          new ConsLoXMLFrag(new Tagged(this.yellTag, 
          new ConsLoXMLFrag(new Tagged(this.italicTag, 
          new ConsLoXMLFrag(new Plaintext("X"), new MtLoXMLFrag())), 
          new ConsLoXMLFrag(new Plaintext("ML"), new MtLoXMLFrag()))), 
          new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag()))));
  
  // ILoXMLFrags
  ILoXMLFrag xml1 = new MtLoXMLFrag();
  ILoXMLFrag xml2 = new ConsLoXMLFrag(new Plaintext("I am "), 
      new ConsLoXMLFrag(new Tagged(new Tag("yell", new MtLoAtt()), 
      new ConsLoXMLFrag(new Plaintext("XML"), new MtLoXMLFrag())), 
      new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag())));
  ILoXMLFrag xml3 = new ConsLoXMLFrag(new Plaintext("I am "),
      new ConsLoXMLFrag(new Tagged(this.yellTag, 
      new ConsLoXMLFrag(new Tagged(this.italicTag, 
      new ConsLoXMLFrag(new Plaintext("X"), new MtLoXMLFrag())), 
      new ConsLoXMLFrag(new Plaintext("ML"), new MtLoXMLFrag()))), 
      new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag())));
  
  // MtLoXMLFrag
  MtLoXMLFrag empty1 = new MtLoXMLFrag();
  
  // ConsLoXMLFrags
  ConsLoXMLFrag xml4 = new ConsLoXMLFrag(new Plaintext("I am "), 
      new ConsLoXMLFrag(new Tagged(new Tag("yell", new MtLoAtt()), 
      new ConsLoXMLFrag(new Plaintext("XML"), new MtLoXMLFrag())), 
      new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag())));
  ConsLoXMLFrag xml5 = new ConsLoXMLFrag(new Plaintext("I am "),
      new ConsLoXMLFrag(new Tagged(this.yellTag, 
      new ConsLoXMLFrag(new Tagged(this.italicTag, 
      new ConsLoXMLFrag(new Plaintext("X"), new MtLoXMLFrag())), 
      new ConsLoXMLFrag(new Plaintext("ML"), new MtLoXMLFrag()))), 
      new ConsLoXMLFrag(new Plaintext("!"), new MtLoXMLFrag())));
  
  // test for sameAttribute method
  boolean testSameAttribute(Tester t) {
    return t.checkExpect(this.volumeAtt1.sameAttribute(this.durationAtt), false)
        && t.checkExpect(this.volumeAtt1.sameAttribute(this.volumeAtt1), true);
  }
 
  // test for sameXMLFrag method
  boolean testSameXMLFrag(Tester t) {
    return t.checkExpect(this.moth.sameXMLFrag(this.moth), true)
        && t.checkExpect(this.moth.sameXMLFrag(this.lamp), false)
        && t.checkExpect(this.moth.sameXMLFrag(this.tagged), false)
        && t.checkExpect(this.tagged.sameXMLFrag(this.moth), false)
        && t.checkExpect(this.tagged.sameXMLFrag(this.tagged), true)
        && t.checkExpect(this.tagged.sameXMLFrag(this.tagged3), false)
        && t.checkExpect(this.tagged3.sameXMLFrag(this.tagged4), true);
  }
  
  // test for samePlaintext
  boolean testSamePlaintext(Tester t) {
    return t.checkExpect(this.moth.samePlaintext(this.moth), true)
        && t.checkExpect(this.moth.samePlaintext(this.lamp), false);
  }
  
  // test for sameTagged
  boolean testSameTagged(Tester t) {
    return t.checkExpect(this.tagged1.sameTagged(this.tagged1), true)
        && t.checkExpect(this.tagged1.sameTagged(this.tagged2), false)
        && t.checkExpect(this.tagged2.sameTagged(this.tagged1), false)
        && t.checkExpect(this.tagged2.sameTagged(this.tagged2), true);
  }
  
  // test for sameTag
  boolean testSameTag(Tester t) {
    return t.checkExpect(this.yellTag.sameTag(this.yellTag), true)
        && t.checkExpect(this.yellTag3.sameTag(this.yellTag3), true)
        && t.checkExpect(this.yellTag.sameTag(this.yellTag3), false)
        && t.checkExpect(this.yellTag3.sameTag(this.yellTag), false);
  }
  
  // test for sameXMLDoc
  boolean testSameXMLDoc(Tester t) {
    return t.checkExpect(this.xml1.sameXMLDoc(this.xml1), true)
        && t.checkExpect(this.xml1.sameXMLDoc(this.xml3), false)
        && t.checkExpect(this.xml3.sameXMLDoc(this.xml1), false)
        && t.checkExpect(this.xml3.sameXMLDoc(this.xml3), true);
  }
  
  // test for sameConsLoXMLFrag
  boolean testSameConsLoXMLFrag(Tester t) {
    return t.checkExpect(this.xml4.sameConsLoXMLFrag(this.xml4), true)
        && t.checkExpect(this.xml4.sameConsLoXMLFrag(this.xml5), false)
        && t.checkExpect(this.xml5.sameConsLoXMLFrag(this.xml4), false)
        && t.checkExpect(this.xml5.sameConsLoXMLFrag(this.xml5), true);
  }
  
  // test for sameLoAtt
  boolean testSameLoAtt(Tester t) {
    return t.checkExpect(this.emptyLoatt.sameLoAtt(this.emptyLoatt), true)
        && t.checkExpect(this.emptyLoatt.sameLoAtt(this.volumeLoatt), false)
        && t.checkExpect(this.volumeLoatt.sameLoAtt(this.volumeLoatt), true)
        && t.checkExpect(this.volumeLoatt.sameLoAtt(this.emptyLoatt), false);
  }
  
  // test for sameConsLoAtt
  boolean testSameConsLoAtt(Tester t) {
    return t.checkExpect(this.volumeLoatt2.sameConsLoAtt(this.volumeLoatt2), true)
        && t.checkExpect(this.volumeLoatt2.sameConsLoAtt(this.durationLoatt2), false)
        && t.checkExpect(this.durationLoatt2.sameConsLoAtt(this.durationLoatt2), true)
        && t.checkExpect(this.durationLoatt2.sameConsLoAtt(this.volumeLoatt2), false);
  }
  
  
  // test for isPlaintext
  boolean testIsPlaintext(Tester t) {
    return t.checkExpect(this.moth.isPlaintext(), true)
        && t.checkExpect(this.tagged1.isPlaintext(), false);
  }
  
  // test for isTagged
  boolean testIsTagged(Tester t) {
    return t.checkExpect(this.moth.isTagged(), false)
        && t.checkExpect(this.tagged1.isTagged(), true);
  }
  
  // test for isMtLoXMLFrag
  boolean testIsMtLoXMLFrag(Tester t) {
    return t.checkExpect(this.empty1.isMtLoXMLFrag(), true)
        && t.checkExpect(this.xml4.isMtLoXMLFrag(), false);
  }
  
  // test for isConsLoXMLFrag
  boolean testIsConsLoXMLFrag(Tester t) {
    return t.checkExpect(this.empty1.isConsLoXMLFrag(), false)
        && t.checkExpect(this.xml4.isConsLoXMLFrag(), true);
  }
  
  // test for isMtLoAtt
  boolean testIsMtLoAtt(Tester t) {
    return t.checkExpect(this.emptyLoatt2.isMtLoAtt(), true)
        && t.checkExpect(this.volumeLoatt2.isMtLoAtt(), false);
  }
  
  // test for isConsLoAtt
  boolean testIsConsLoAtt(Tester t) {
    return t.checkExpect(this.emptyLoatt2.isConsLoAtt(), false)
        && t.checkExpect(this.volumeLoatt2.isConsLoAtt(), true);
  }

  
}

