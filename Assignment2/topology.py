#!/usr/bin/python

"""
This example shows how to create an empty Mininet object
(without a topology object) and add nodes to it manually.
"""

from mininet.net import Mininet
from mininet.node import Controller
from mininet.cli import CLI
from mininet.log import setLogLevel, info
import sys

def emptyNet():

    "Create an empty network and add nodes to it."
    noswitch=int(sys.argv[1])
    nohost=int(sys.argv[2])
    net = Mininet( controller=Controller )

    info( '*** Adding controller\n' )
    net.addController( 'c0' )
   
    switches=[]
    info( '*** Adding hosts\n' )
    count = 0;
    for i in range(noswitch):
    	s = net.addSwitch('s'+str(i+1))
        for j in range(nohost):
		count=count+1;
		if( count  % 2 == 0):
			h=net.addHost('h'+str(count),ip='10.0.2.0/24',defaultRoute=None)
			net.addLink(h,s);
		else:
			h=net.addHost('h'+str(count),ip='10.0.1.0/24',defaultRoute=None)
			net.addLink(h,s);
    	switches.append(s);
   
    info( '*** Creating links\n' )
    for i in range(noswitch):
	net.addLink(switches[i%noswitch],switches[(i+1)%noswitch])

    info( '*** Starting network\n')
    net.start()

    info( '*** Running CLI\n' )
    CLI( net )

    info( '*** Stopping network' )
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    emptyNet()
