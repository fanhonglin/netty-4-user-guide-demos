package com.waylau.netty.demo.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Echo Server Handler.
 * 
 * @since 1.0.0 2019年10月2日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
//@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println(ctx.channel().remoteAddress() + " -> Server :" + msg);

		ByteBuf byteBuf = (ByteBuf) msg;

		System.out.println("服务端接受到的消息：" + byteBuf.toString(Charset.defaultCharset()));


		// 写消息到管道
		ctx.write(msg);// 写消息
		ctx.flush(); // 冲刷消息
		
		// 上面两个方法等同于 ctx.writeAndFlush(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}