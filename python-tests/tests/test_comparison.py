from algorithms.bfs import bfs as python_bfs
from algorithms.dfs import dfs as python_dfs
from utils.api_client import GraphEngineClient

def test_bfs_comparison():
    """Compare BFS Python vs Java"""
    client = GraphEngineClient()
    
    graph_data = client.get_graph(graph_id=4)
    adjacency = client.graph_to_adjacency_dict(graph_data)
    
    python_result = python_bfs(adjacency, start=3)
    print(f"BFS Python: {python_result}")
    
    java_result = client.bfs(graph_id=4, start_node_id=3)
    print(f"BFS Java: {java_result['visitedNodeIds']}")
    
    assert python_result == java_result['visitedNodeIds'], "Résultats différents !"
    print("BFS Python et Java donnent le même résultat !")

def test_dfs_comparison():
    """Compare DFS Python vs Java"""
    client = GraphEngineClient()
    
    graph_data = client.get_graph(graph_id=4)
    adjacency = client.graph_to_adjacency_dict(graph_data)
    
    python_result = python_dfs(adjacency, start=3)
    print(f"DFS Python: {python_result}")
    
    java_result = client.dfs(graph_id=4, start_node_id=3)
    print(f"DFS Java: {java_result['visitedNodeIds']}")
    
    assert python_result == java_result['visitedNodeIds'], "Résultats différents !"
    print("DFS Python et Java donnent le même résultat !")

if __name__ == "__main__":
    print("=== Test BFS ===")
    test_bfs_comparison()
    print("\n=== Test DFS ===")
    test_dfs_comparison()