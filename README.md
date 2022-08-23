# Directed Weighted Graph

#### A Directed Weighted Graph is a graph that is made up of a set of vertices connected by directed edges , each edge has a deriction and weight .

#### In formal terms, a directed graph is an ordered pair G = (V, E) where : \
#### V is a set whose elements are called vertices, nodes, or points.\
##### E is a set of ordered pairs of vertices, called  directed edges , arrows, or directed lines.


##### -Class NodeDataImpl : This class implements the NodeData interface , wich represents a single Node .\
##### A Node has :\
##### key : the id of the Node\
##### weight : does not change anything in this project\
##### info : represents information about the node , used in DirectedWeightedGraphAlgorithms class implementation\
##### color : represents Node color , used in DirectedWeightedGraphAlgorithms class implementation\
##### location (instance of GeoLocation) : represents the node's location (x,y,z) 

##### -Class EdgeDataImpl : This class implements The EdgeData interface , wich represents a single Edge.\
##### An Edge has :\
##### src (source) : the source node id of the edge\
##### dst (destination) : the destination node id of the edge\
##### weight : the weight of the edge 

##### -Class geo_loc : This class is used to represent a node's location\ 
##### A location has x,y,z 

##### *In our project , We represented The Graph (Nodes and Edges) By two HashMaps :\
#####  The first one is for the nodes , the HashMap 'keys' is the id of the node , and the 'values' is the Node itself (NodeData).\
##### The second one is for the edges , the 'keys' are the graph nodes , and each key is represented by a hashmap (value) wich contains all the edges getting out from the key      node. \
 
##### HashMap<Integer,EdgeData> , the Integer is the destination node id, the EdgeData is the edge from keynode to destination node.

##### -Class DirectedWeightedGraphImpl : This class implements DirectedWeightedGraph interface.\
As we mentioned , this class has two private HashMaps.

##### This class has thre main constructors , one to init the graph , one for a deep copy , and one inits a graph from json  

##### This class has multiple functions that work on the graph : \
##### addNode : adds a new node to the graph\
##### removeNode : removes a node from the graph\
##### addEdge : adds a new edge to the graph \
##### removeEdge : removes an edge from the graph \
##### connect : adds a new edge connecting between two nodes in the graph (adds a directed edge to the graph from the source node -> to the destination node)\
##### nodeSize: returns the number of the nodes in the graph \
##### edgeSize : returns the number of the edges in the graph\
##### getMC : returns the modecounter of the graph (with each change applied to thne graph , the modecounter changes)\
##### json_obj_Node : converts json object (Node object in json) to a Node (new Node)\
##### json_obj_edge : converts json object (Edge object in json) to an Edge (new Edge)\
##### nodeIter : returns an iterator of all all the nodes in the graph\
##### edgeIter : returns an iterator of all the edges in the graph \

##### -Class DirectedWeightedGraphAlgorithmsImpl : This class implements DirectedWeightedGraphAlgorithms interface\
##### This class has multiple algorithms that work on a given graph :\
##### init : inits the given graph to apply algorithms on it \ 
##### getGraph : return this graph \
##### copy : copies the graph \
##### isConnected : returns true if and only if there is a valid path from each node to each other node  : \
##### we iterate through the nodes in the graph , for each node iteration we apply the function "start" wich colors all the nodes white and sets the information for          each  node   as null\  
#####  Then , we apply the algorithm (function) "DFS" wich takes the graph and a given node (the iteration node) (As explained below) \
#####  Then , after applying DFS with a given node in the graph we iterate through the nodes and if there is a node that is not black , that means that there is no      #####  path between 
#####  the two nodes , return false , the graph is not connected  \

##### center : returns the node with the minimum distance from all other nodes (minimizes the max distance to all other nodes).\
##### First , we check if the graph is connected , if not return null , \
##### We iterate through all the nodes in the graph , we define a variable to sum all the distances for this node and initialize it 0 ,for each node in the iteration ##### we compute the shortest distance with each other node(second iteration) \ 
##### We put every node (node id) with the computed sum of distances in a HashMap , go on the HashMap and find the node with the minimum sum of distances (value) ,\
##### Return that Node (It's the center of the graph)\

##### shortestpathdist : returns the the length of the shortest path between two nodes (sum of edges weights)\
##### First we call the function "start" wich colors all the nodes white and sets the information for each node as null , and the weight of the node as Infinity \
##### Then , we defined a queue , added the given source node to the queue , and set the weight of the source node 0\
##### while source node is not null , we set the color of the source node to black , we iteratre on the edges that go out from the source node \
##### For each edge in the iteration we get the destination node of the edge (neighbor node) . 
##### Then we check if the neighbor node weight is greater than the source weight + edge weight , if so , then we set the info of the neighbor node as the source   ##### key(the  key of the node wich discovered this node) , set the weight of the neighbor node to the  computed wiegth(src weigth + edge weigth), add the neighbor ##### node to the queue/
##### in the end return the -1 if there is no path between the given source and destintion nodes/
##### else return the wieght of the destintion node(minimum wiegth from src to dest computed).

##### shortestpath : returns a list of the sortest path from given source to given destination (path is a list of nodes)
##### First , we check if a path exists between the source and the destination using the "shortestpathdist" function 
##### If there is no path , return null .
##### If there is a path , by using the "shortestpathdist" function , we computed the shortest path from source to destination (we updated the weights and info)
##### We iterate starting from the destination node info to the source node info (the shortest path) 
##### Each time in the iteration from dest to src , we added the info of each node(each node contains info which represents the node that discoverd this node) to a ##### list
##### reaching the source node.



##### Testing The Graph on 1000,10000,10000 nodes json file:

##### 1000 nodes json file:

##### isConnected - 0.6 seconds in avarage
##### center - 10 menuites in avarage
##### TSP - 0.015 seconds in average
##### shortestpath - 0.015 seconds in average




*UML DIAGRAM :


![מדעי המחשב](https://user-images.githubusercontent.com/94143804/146603935-cb2fe125-65f2-47d9-997b-27eccaca0755.png)



