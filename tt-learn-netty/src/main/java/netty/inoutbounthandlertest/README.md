## 自定义协议发送Long型数据

ByteToMessageDecoder 继承于  ChannelInboundHandler

MessageToByteEncoder 继承于 ChannelInboundHandler

------

这两个类在pipline里面ChannelInboundHandler是入站，ChannelInboundHandler是出站，可以根据类型判断到底是进还是出，所以顺序不会乱

------

使用ByteToMessageDecoder或者MessageToByteEncoder解码long类型的时候必须判断长度是否为long的长度（8）

使用ReplayingDecoder可以不用判断长度，这个类可以自动帮我们判断