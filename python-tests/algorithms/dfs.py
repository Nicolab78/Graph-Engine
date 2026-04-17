from typing import List, Dict, Set

def dfs(graph: Dict[int, List[int]], start: int) -> List[int]:
    """
    Parcours DFS (Depth-First Search)
    
    Args:
        graph: Dictionnaire {node_id: [neighbor_ids]}
        start: ID du nœud de départ
        
    Returns:
        Liste des IDs dans l'ordre de visite
    """
    visited_order = []
    visited = set()
    stack = [start]
    visited.add(start)
    
    while stack:
        current = stack.pop() 
        visited_order.append(current)
        
        for neighbor in graph.get(current, []):
            if neighbor not in visited:
                visited.add(neighbor)
                stack.append(neighbor)
    
    return visited_order