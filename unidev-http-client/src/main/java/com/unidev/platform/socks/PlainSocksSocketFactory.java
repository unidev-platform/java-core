/*
  Copyright (c) 2018 Denis O <denis.o@linux.com>

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.unidev.platform.socks;

import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PlainSocksSocketFactory implements ConnectionSocketFactory {
    private String socksIP;
    private int socksPort;

    public PlainSocksSocketFactory(String socksIP, int socksPort) {
        this.socksIP = socksIP;
        this.socksPort = socksPort;
    }

    @Override
    public Socket createSocket(HttpContext context) throws IOException {
        return new Socket();
    }

    @Override
    public Socket connectSocket(int connectTimeout, Socket sock, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {

        InetSocketAddress socksaddr = new InetSocketAddress(socksIP, socksPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksaddr);
        Socket socks = new Socket(proxy);

        socks.bind(localAddress);

        try {
            socks.connect(remoteAddress, connectTimeout);
        } catch (SocketTimeoutException ex) {
            throw new ConnectTimeoutException("Connect to " + remoteAddress.getHostName() + "/" + remoteAddress.getAddress() + " timed out");
        }
        return socks;
    }
}
