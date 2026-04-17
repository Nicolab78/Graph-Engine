import requests
from typing import Dict, List

class GraphEngineClient:
    def __init__(self, base_url: str = "http://localhost:8080/api"):
        self.base_url = base_url
    
    def get_graph(self, graph_id: int) -> Dict:
        """Récupère un graphe avec tous ses détails"""
        response = requests.get(f"{self.base_url}/graphs/{graph_id}/detail")
        response.raise_for_status()
        return response.json()
    
    def bfs(self, graph_id: int, start_node_id: int) -> Dict:
        """Appelle l'API BFS Java"""
        response = requests.get(f"{self.base_url}/graphs/{graph_id}/bfs/{start_node_id}")
        response.raise_for_status()
        return response.json()
    
    def dfs(self, graph_id: int, start_node_id: int) -> Dict:
        """Appelle l'API DFS Java"""
        response = requests.get(f"{self.base_url}/graphs/{graph_id}/dfs/{start_node_id}")
        response.raise_for_status()
        return response.json()
    
    def graph_to_adjacency_dict(self, graph_data: Dict) -> Dict[int, List[int]]:
        """Convertit le graphe API en dictionnaire d'adjacence Python"""
        adjacency = {node['id']: [] for node in graph_data['nodes']}
        
        for edge in graph_data['edges']:
            adjacency[edge['sourceId']].append(edge['targetId'])

            if not graph_data['directed']:
                adjacency[edge['targetId']].append(edge['sourceId'])
        
        return adjacency