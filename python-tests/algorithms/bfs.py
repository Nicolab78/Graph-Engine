from collections import deque
from typing import List, Dict, Set

def bfs(graph: Dict[int, List[int]], start: int) -> List[int]:
    """
    Parcours BFS (Breadth-First Search)
    
    Args:
        graph: Dictionnaire {node_id: [neighbor_ids]}
        start: ID du nœud de départ
        
    Returns:
        Liste des IDs dans l'ordre de visite
    """
    visited_order = []
    visited = set()
    queue = deque([start])
    visited.add(start)
    
    while queue:
        current = queue.popleft()
        visited_order.append(current)
        
        for neighbor in graph.get(current, []):
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
    
    return visited_order